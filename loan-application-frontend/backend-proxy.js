// Configuration
const PORT = 3040;
const HOST = 'localhost';
const API_SERVICE_URL = 'http://localhost:9090';

// Proxy server
const express = require('express');
const morgan = require('morgan');
const { createProxyMiddleware } = require('http-proxy-middleware');

var cors = require('cors');

const app = express();
app.use(cors());
app.use(morgan('dev'));
app.use('', createProxyMiddleware({
  target: API_SERVICE_URL,
  changeOrigin: true,
  onProxyRes: function onProxyRes(proxyRes, req, res) {
    proxyRes.headers['access-control-allow-origin'] = req.headers.origin;
    proxyRes.headers['access-control-allow-credentials'] = 'true';
    //proxyRes.headers['access-control-allow-headers'] = req.headers['access-control-request-headers'];
  }
}));
app.listen(PORT, HOST, () => console.log(`Starting Proxy at ${HOST}:${PORT}`));
