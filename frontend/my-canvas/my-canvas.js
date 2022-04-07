import {css, html, LitElement} from 'lit';
import {createRef, ref} from 'lit/directives/ref';
import {styleMap} from 'lit/directives/style-map.js';

class MyCanvas extends LitElement {
    constructor() {
        super();
        this.canvasRef = createRef();
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
        }
    }

    static get properties() {
        return {
            scale: {state: true},
            isGrabbing: {state: true},
            canvasStyleMap: {state: true},
            x: {state: true},
            y: {state: true},
        }
    };

    static get styles() {
        return css`
            canvas {
                width: 500px;
                height: 500px;
                image-rendering: -moz-crisp-edges;
                image-rendering: -webkit-crisp-edges;
                image-rendering: pixelated;
                image-rendering: crisp-edges;
                cursor: grab;
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
            <canvas
                    @wheel=${this.onWheel}
                    @mousemove=${this.onMouseMove}
                    @mousedown=${(event) => {
                        this.mousePosition.initialX = event.clientX - this.mousePosition.xOffset;
                        this.mousePosition.initialY = event.clientY - this.mousePosition.yOffset;
                        this.isGrabbing = true
                    }}
                    @mouseup=${() => {
                        this.mousePosition.initialX = this.x;
                        this.mousePosition.initialY = this.y;
                        this.isGrabbing = false
                    }}
                    width="2px"
                    height="2px"
                    style=${styleMap(this.canvasStyleMap)}
                    ${ref(this.canvasRef)}
            ></canvas>
        `;
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

        this.scale = this.scale * scaleDiff;
    }

    async setImageData(jsonData) {
        // TODO: komplettes ImageData Objekt (https://developer.mozilla.org/en-US/docs/Web/API/ImageData) von Server an Client senden
        // TODO: Scaling Mechanismus auf Browserseite implementieren
        const imageFromServer = JSON.parse(jsonData);
        const ctx = this.canvasRef.value.getContext('2d');
        ctx.clearRect(0, 0, 2, 2);
        // https://github.com/GoogleChromeLabs/squoosh/blob/dev/src/client/lazy-app/util/canvas.ts
        // https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/createImageData
        const imageData = ctx.createImageData(2, 2);

        for (let i = 0; i < imageData.data.length; i += 4) {
            // Modify pixel data
            imageData.data[i] = imageFromServer[i];          // R value
            imageData.data[i + 1] = imageFromServer[i + 1];  // G value
            imageData.data[i + 2] = imageFromServer[i + 2];  // B value
            imageData.data[i + 3] = imageFromServer[i + 3];  // A value
        }
        ctx.putImageData(imageData, 0, 0);
    }
}

window.customElements.define('my-canvas', MyCanvas);