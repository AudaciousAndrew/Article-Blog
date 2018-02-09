const path = require('path');

module.exports = {
  entry: './src/index.js',
  output: {
    filename: './static/js/bundle.js',
    path: path.resolve(__dirname, 'build')
  },
  module: {
        loaders: [
            {
              test: /\.js/,
              loader: 'babel-loader',
              exclude: [ /node_modules(?!\/quill)/, /bowercomponents/ ],
              query: {
                cacheDirectory: true,
                presets: ['react', 'es2015']
              }
            },
            {
              test: /(\.scss|\.css)$/,
              loader: 'style-loader!css-loader!sass-loader'
            }
        ]
    }
};
