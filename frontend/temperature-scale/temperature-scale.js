import {css, html, LitElement} from 'lit';
import {styleMap} from 'lit/directives/style-map.js';

class TemperatureScale extends LitElement {
    constructor() {
        super();
        this.temperatureBarStyleMap = {
            background: `black`
        };
    }

    static get properties() {
        return {
            steps: {property: true},
            temperatureBarStyleMap: {state: true},
        }
    }

    static get styles() {
        return css`
            .root {
                display: flex;
                flex-wrap: nowrap;
                height: 100%;
                align-items: stretch;
                padding-left: 8px;
            }
            
            .bar {
                width: 16px;
                margin-top: 0.8rem;
                margin-bottom: 0.65rem;
            }
            
            .legend {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                align-items: stretch;
                padding-left: 4px;
            }
            
            .legend > span::before {
                content: "- ";
            }
        `;
    }


    willUpdate(_changedProperties) {
        super.willUpdate(_changedProperties);
        if (_changedProperties.has('steps')) {
            this.temperatureBarStyleMap = {
                background: `linear-gradient(${Object.values(this.steps).reverse().join(',')})`
            };
        }
    }

    render() {
        return html`
            <div class="root">
                <div class="bar" style=${styleMap(this.temperatureBarStyleMap)}>
                </div>
                <div class="legend">
                    ${Object.keys(this.steps).reverse().map(step => html`<span>${step} °C</span>`)}
                </div>
            </div>
        `;
    }
}

window.customElements.define('temperature-scale', TemperatureScale);