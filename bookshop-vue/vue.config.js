module.exports = {
    devServer: {
      proxy: {
        '/api': {
          target: 'http://localhost:8443',
          changeOrigin: true,
          ws: true,
          pathRewrite: {
            '^/api': ''
          }
        }
      }
    }
  }