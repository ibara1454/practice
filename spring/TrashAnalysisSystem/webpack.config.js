const path = require('path');

module.exports = {
  mode: 'development',
  entry: ['babel-polyfill', 'whatwg-fetch', './src/main/js/app.js'],
  output: {
    filename: 'bundle.js',
    path: path.join(__dirname, 'src/main/resources/static/js')
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: [
          {
            loader: 'babel-loader'
          }
        ]
      }
    ]
  }
};
