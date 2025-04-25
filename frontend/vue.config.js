module.exports = {
  outputDir: 'dist',
  publicPath: '/',
  devServer: {
    proxy: 'http://localhost:8080'
  }
}