console.log('trek sdk init start')

;(function (w, d, s, src, n) {
  let js,
    ajs = d.getElementsByTagName(s)[0]
  if (d.getElementById(n)) return
  js = d.createElement(s)
  js.id = n
  w[n] =
    w[n] ||
    function () {
      ;(w[n].q = w[n].q || []).push(arguments)
    }
  w[n].l = 1 * new Date()
  js.async = 1
  js.src = src
  ajs.parentNode.insertBefore(js, ajs)
})(
  window,
  document,
  'script',
  'https://static.aottercdn.com/trek/sdk/3.5.4/sdk.js',
  'AotterTrek'
)

window.AotterTrek('setUser', {
  email: 'steven.ho@aotter.net',
  hashedEmail: '123456',
  firstName: 'Steven',
  lastName: 'Ho',
  phone: '0900000000',
  hashedPhone: '123456',
  gender: 'Male',
  city: 'Taipei',
  state: 'Xinyi',
  zip: '110',
  birthYear: '1996',
  birthMonth: '08',
  birthDate: '11',
  fbId: '123456'
})

window.AotterTrek('init', 'yEFcFoJaruNorh5RqtuR')