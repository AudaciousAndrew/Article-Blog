const path = require('path');
const webpack = require('webpack');


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
            },
            { test: /\.(png|woff|woff2|eot|ttf|svg|gif)$/, loader: 'url-loader?limit=100000' }

        ]
    },
    plugins: [
        new webpack.optimize.OccurrenceOrderPlugin(),
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoEmitOnErrorsPlugin()
    ]
};
