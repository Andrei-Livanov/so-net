import Vue from 'vue';
import Vuetify from 'vuetify';
import '@babel/polyfill';
import 'vuetify/dist/vuetify.min.css';

import 'api/resource';
import router from 'router/router';
import store from 'store/store';


import App from 'pages/App.vue';
import { connect } from './util/ws';

import * as Sentry from '@sentry/browser';
import { Vue as VueIntegration } from '@sentry/integrations';

Sentry.init({
  dsn: 'https://752ad12f072f48ca84bbd437b28a52fc@o913126.ingest.sentry.io/5850695',
  integrations: [new VueIntegration({Vue, attachProps: true})],
});

Sentry.setUser({
    id: profile && profile.id,
    username: profile && profile.name
 });

if (profile) {
    connect();
}

Vue.use(Vuetify)

new Vue({
    vuetify : new Vuetify({
        icons: {
            iconfont: 'mdiSvg'
        }
    }),
    el: '#app',
    store,
    router,
    render: a => a(App)
});
