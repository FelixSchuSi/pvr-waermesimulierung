import {css, html, LitElement} from 'lit';
import {createRef, ref} from 'lit/directives/ref';
import {styleMap} from 'lit/directives/style-map.js';
import '../temperature-scale/temperature-scale';

class MyCanvas extends LitElement {
    constructor() {
        super();
        this.canvasRef = createRef();
        this.canvasWrapperRef = createRef();
        this.scale = 1;
        this.x = 0;
        this.y = 0;
        this.canvasStyleMap = {
            transform: `translate(${this.x}px, ${this.y}px) scale(${this.scale})`,
            cursor: 'grab'
        };
        this.isGrabbing = false;
        this.mousePosition = {
            initialX: 0,
            initialY: 0,
            xOffset: 0,
            yOffset: 0
        };
        this.imageDataHeight = 0;
        this.imageDataWidth = 0;
        this.steps = {};
        this.addEventListener('mousemove', this.onMouseMove);
        this.addEventListener('mouseup', this.onMouseUp);
    }

    static get properties() {
        return {
            scale: {state: true},
            isGrabbing: {state: true},
            canvasStyleMap: {state: true},
            x: {state: true},
            y: {state: true},
            imageDataWidth: {state: true},
            imageDataHeight: {state: true},
            steps: {state: true}
        }
    };

    static get styles() {
        return css`
            canvas {
                image-rendering: -moz-crisp-edges;
                image-rendering: -webkit-crisp-edges;
                image-rendering: pixelated;
                image-rendering: crisp-edges;
                height: 50vh;
                width: 50vh;
            }
            
            .canvas-wrapper {
                display: flex;
                flex-wrap: nowrap;
                transform-origin: 0 0;
                will-change: transform;
            }
            
            :host {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                overflow: hidden;
                display: grid;
                place-items: center;
            }
        `;
    }

    willUpdate(_changedProperties) {
        super.willUpdate(_changedProperties);
        if (_changedProperties.has('scale') || _changedProperties.has('x') || _changedProperties.has('y')) {
            this.canvasStyleMap = {
                ...this.canvasStyleMap,
                transform: `translate(${this.x}px, ${this.y}px) scale(${this.scale})`
            };
        }
        if (_changedProperties.has('isGrabbing')) {
            this.canvasStyleMap = {...this.canvasStyleMap, cursor: this.isGrabbing ? 'grabbing' : 'grab'};
        }
    }

    render() {
        return html`
            <div class="canvas-wrapper"
                 @mousedown=${(event) => {
                     this.mousePosition.initialX = event.clientX - this.mousePosition.xOffset;
                     this.mousePosition.initialY = event.clientY - this.mousePosition.yOffset;
                     this.isGrabbing = true
                 }}
                 @wheel=${this.onWheel}
                 ${ref(this.canvasWrapperRef)}
                 style=${styleMap(this.canvasStyleMap)}>
                <canvas
                        width="${this.imageDataWidth}px"
                        height="${this.imageDataHeight}px"
                        ${ref(this.canvasRef)}
                ></canvas>
                <temperature-scale .steps=${this.steps}></temperature-scale>
            </div>
        `;
    }

    onMouseUp() {
        this.mousePosition.initialX = this.x;
        this.mousePosition.initialY = this.y;
        this.isGrabbing = false
    }

    onMouseMove(event) {
        if (!this.isGrabbing) return;

        this.x = event.clientX - this.mousePosition.initialX;
        this.y = event.clientY - this.mousePosition.initialY;

        this.mousePosition.xOffset = this.x;
        this.mousePosition.yOffset = this.y;
    }

    onWheel(event) {
        event.preventDefault();
        let {deltaY} = event;
        const {deltaMode} = event;

        if (deltaMode === 1) {
            // 1 is "lines", 0 is "pixels"
            // Firefox uses "lines" for some types of mouse
            deltaY *= 15;
        }

        const zoomingOut = deltaY > 0;
        const ratio = 1 - (zoomingOut ? -deltaY : deltaY) / 300;
        const scaleDiff = zoomingOut ? 1 / ratio : ratio;

        const newScale = this.scale * scaleDiff;
        const currentRect = this.canvasWrapperRef.value.getBoundingClientRect();
        const originX = event.clientX - currentRect.left;
        const originY = event.clientY - currentRect.top;
        let matrix = document.createElementNS('http://www.w3.org/2000/svg', 'svg').createSVGMatrix();

        matrix = matrix
            .translate(originX, originY)
            .translate(this.x, this.y)
            .scale(scaleDiff)
            .translate(-originX, -originY)
            .scale(newScale);

        this.mousePosition.xOffset = matrix.e;
        this.mousePosition.yOffset = matrix.f;

        this.scale = newScale;
        this.x = matrix.e;
        this.y = matrix.f;
    }

    async setImageData(jsonData) {
        const {width, height, data} = JSON.parse(jsonData);
        if (width !== this.imageDataWidth || height !== this.imageDataHeight) {
            this.imageDataWidth = width;
            this.imageDataHeight = height;
            // Es wird ein Update durch das Setzen von this.imageDataWidth und this.imageDataHeight ausgelöst.
            // Wir müssen sicherstellen, dass das Canvas Element bereits die neue Größe bekommen hat, sonst
            // wird das Bild nicht richtig dargestellt.
            await this.updateComplete;
        }

        const ctx = this.canvasRef.value.getContext('2d');
        ctx.clearRect(0, 0, width, height);
        const imageData = new ImageData(Uint8ClampedArray.from(data), width, height);
        ctx.putImageData(imageData, 0, 0);
    }

    async setTemperatureScaleData(jsonData) {
        this.steps = JSON.parse(jsonData).data;
    }
}

window.customElements.define('my-canvas', MyCanvas);