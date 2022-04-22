import {css, html, LitElement} from 'lit';
import {styleMap} from 'lit/directives/style-map.js';

class TemperatureScale extends LitElement {
    constructor() {
        super();
        this.steps = {
            '0': 'rgb(255, 0, 0)',
            '25': 'green',
            '50': 'blue',
            '75': 'yellow',
            '100': 'black',
        };
        this.temperatureBarStyleMap = {
            background: `linear-gradient(red, yellow, blue, orange)`
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
            }
            
            .legend {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                align-items: stretch;
            }
            
            .legend {
                padding-left: 8px;
            }
        `;
    }


    willUpdate(_changedProperties) {
        super.willUpdate(_changedProperties);
        if (_changedProperties.has('steps')) {
            this.temperatureBarStyleMap = {
                background: `linear-gradient(${Object.values(this.steps).join(',')})`
            };
        }
    }

    render() {
        return html`
            <div class="root">
                <div class="bar" style=${styleMap(this.temperatureBarStyleMap)}>
                </div>
                <div class="legend">
                    ${Object.keys(this.steps).map(step => html`<span>${step}</span>`)}
                </div>
            </div>
        `;
    }
}

window.customElements.define('temperature-scale', TemperatureScale);