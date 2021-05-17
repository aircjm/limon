const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
    purge: {
        safeList: [],
        content: [
            './index.html',
            './src/**/*.jsx',
            './src/**/*.js',
        ],
    },
    theme: {
        extend: {
            fontWeight: ['hover', 'focus'],
            fontFamily: {
                sans: ['Inter var', ...defaultTheme.fontFamily.sans],
            },
        },
    },
    variants: {},
    plugins: []
};