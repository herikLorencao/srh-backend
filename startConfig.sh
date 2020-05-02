sudo yarn global add commitizen
echo "module.exports = {extends: ['@commitlint/config-conventional']}" > commitlint.config.js
yarn install
