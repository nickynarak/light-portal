// @flow
import React from 'react';
import ReactDOM from 'react-dom';
import store from './store/configureStore';

import App from './components/App';
import routes from './routes';
import registerServiceWorker from './registerServiceWorker';

/**
 * Define context for Application
 */
const context = {
  store,
  routes,
};

ReactDOM.render(<App context={context} />, document.getElementById('root'));

registerServiceWorker();
