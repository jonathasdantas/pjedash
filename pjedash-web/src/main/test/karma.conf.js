// Karma configuration
// http://karma-runner.github.io/0.12/config/configuration-file.html
// Generated on 2015-12-08 using
// generator-karma 1.0.1

module.exports = function(config) {
  'use strict';

  config.set({
    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // base path, that will be used to resolve files and exclude
    basePath: '../',

    // testing framework to use (jasmine/mocha/qunit/...)
    // as well as any additional frameworks (requirejs/chai/sinon/...)
    frameworks: [
      "jasmine"
    ],

    // list of files / patterns to load in the browser
    files: [
      // bower:js
      'webapp/bower_components/jquery/dist/jquery.js',
      'webapp/bower_components/angular/angular.js',
      'webapp/bower_components/bootstrap/dist/js/bootstrap.js',
      'webapp/bower_components/angular-animate/angular-animate.js',
      'webapp/bower_components/angular-aria/angular-aria.js',
      'webapp/bower_components/angular-cookies/angular-cookies.js',
      'webapp/bower_components/angular-resource/angular-resource.js',
      'webapp/bower_components/angular-sanitize/angular-sanitize.js',
      'webapp/bower_components/angular-ui-router/release/angular-ui-router.js',
      'webapp/bower_components/angular-messages/angular-messages.js',
      'webapp/bower_components/angular-material/angular-material.js',
      'webapp/bower_components/Chart.js/Chart.js',
      'webapp/bower_components/angular-chart.js/dist/angular-chart.js',
      'webapp/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
      'webapp/bower_components/rangy/rangy-core.js',
      'webapp/bower_components/rangy/rangy-classapplier.js',
      'webapp/bower_components/rangy/rangy-highlighter.js',
      'webapp/bower_components/rangy/rangy-selectionsaverestore.js',
      'webapp/bower_components/rangy/rangy-serializer.js',
      'webapp/bower_components/rangy/rangy-textrange.js',
      'webapp/bower_components/textAngular/dist/textAngular.js',
      'webapp/bower_components/textAngular/dist/textAngular-sanitize.js',
      'webapp/bower_components/textAngular/dist/textAngularSetup.js',
      'webapp/bower_components/angular-bootstrap-colorpicker/js/bootstrap-colorpicker-module.js',
      'webapp/bower_components/satellizer/dist/satellizer.js',
      'webapp/bower_components/blob-polyfill/Blob.js',
      'webapp/bower_components/file-saver.js/FileSaver.js',
      'webapp/bower_components/angular-file-saver/dist/angular-file-saver.bundle.js',
      'webapp/bower_components/jqcloud2/dist/jqcloud.js',
      'webapp/bower_components/angular-jqcloud/angular-jqcloud.js',
      'webapp/bower_components/angular-base64-upload/src/angular-base64-upload.js',
      'webapp/bower_components/angular-mocks/angular-mocks.js',
      // endbower
      "app/scripts/**/*.js",
      "test/mock/**/*.js",
      "test/spec/**/*.js"
    ],

    // list of files / patterns to exclude
    exclude: [
    ],

    // web server port
    port: 8080,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: [
      "PhantomJS"
    ],

    // Which plugins to enable
    plugins: [
      "karma-phantomjs-launcher",
      "karma-jasmine"
    ],

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false,

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO,

    // Uncomment the following lines if you are using grunt's server to run the tests
    // proxies: {
    //   '/': 'http://localhost:9000/'
    // },
    // URL root prevent conflicts with the site root
    // urlRoot: '_karma_'
  });
};
