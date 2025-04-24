module.exports = {
  outputDir: '../backend/src/main/resources/static',          // Сборка в frontend/dist/
  publicPath: '/',           // Базовый URL
  devServer: {
    proxy: 'http://localhost:8080'  // Прокси для API в dev-режиме
  }
}