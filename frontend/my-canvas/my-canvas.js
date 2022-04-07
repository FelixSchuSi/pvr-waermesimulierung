import {css, html, LitElement} from 'lit';
import {createRef, ref} from 'lit/directives/ref';
import {styleMap} from 'lit/directives/style-map.js';

class MyCanvas extends LitElement {
    constructor() {
        super();
        this.canvasRef = createRef();
        this.scale = 1;
        this.canvasStyleMap = {
            x: 0,
            y: 0,
            transform: `scale(${this.scale})`
        };
    }

    static get properties() {
        return {
            canvasStyleMap: {
                state: true
            }
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
            }
        `;
    }

    render() {
        return html`
            <canvas
                    @wheel=${this.onWheel}
                    width="2px"
                    height="2px"
                    style=${styleMap(this.canvasStyleMap)}
                    ${ref(this.canvasRef)}
            ></canvas>
        `;
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
        this.canvasStyleMap = {...this.canvasStyleMap, transform: `scale(${this.scale})`};
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