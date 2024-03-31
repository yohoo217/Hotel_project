console.log('trek sdk send data & get ad')

// 送使用者資料
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

// 要廣告
// doc: https://aotter.gitbook.io/web/web/enable-test-ads

// 置中
window.AotterTrek('suprAd', {
  place: '5a41c4d0-b268-43b2-9536-d774f46c33bf', // 300x250
  selector: '#trek-ad-ptt-article-middle',
  onAdLoad() {
    console.log('trek ad loaded')
  },
  onAdFail() {
    console.warn('trek ad fail')
  }
})
