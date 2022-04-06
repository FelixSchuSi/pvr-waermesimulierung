import { html, LitElement, css } from 'lit';
import { createRef, ref } from 'lit/directives/ref';

class MyCanvas extends LitElement {
    static get styles() {
        return css`
            canvas {
                width: 500px;
                height: 500px;
                image-rendering: -moz-crisp-edges;
                image-rendering: -webkit-crisp-edges;
                image-rendering: pixelated;
                image-rendering: crisp-edges;
            }
        `;
    }
    constructor() {
        super();
        this.canvasRef = createRef();
    }

    render() {
        return html`
            <canvas width="2px" height="2px" ${ref(this.canvasRef)}></canvas>
    `;
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
            imageData.data[i] = imageFromServer[i];        // R value
            imageData.data[i + 1] = imageFromServer[i+1];  // G value
            imageData.data[i + 2] = imageFromServer[i+2];  // B value
            imageData.data[i + 3] = imageFromServer[i+3];  // A value
        }
        ctx.putImageData(imageData, 0, 0);
    }
}

window.customElements.define('my-canvas', MyCanvas);