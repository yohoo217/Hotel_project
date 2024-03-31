const mediaCardEle = document.querySelector('#media-card')

const title = getQueryString('media-title') || ''
const desc = getQueryString('media-desc') || ''
const url = getQueryString('media-url') || ''
const sponsor = getQueryString('media-sponsor') || ''
const cta = getQueryString('media-cta') || '瞭解詳情'
// const img = getQueryString('media-img') || '' // 目前用不到

mediaCardEle.innerHTML = /*HTML*/ `
  <div onClick="window.open('${url}')">
    <div class="card__title">${title}</div>
    <div class="card__desc">${desc}</div>
    <div class="card__footer">
      <div class="card__sponsor">${sponsor}</div>
      <div class="card__cta">${cta}</div>
    </div>
  </div>
`

function getQueryString(name) {
  var url_string = window.location.href
  var params = url_string.split('?')[1]
  if (!params) return null
  var vars = params.split('&')
  // var query_string = {};
  for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split('=')
    var key = decodeURIComponent(pair[0])
    var value = decodeURIComponent(pair[1])
    if (name === key) {
      return value
    }
  }
  return null
}
