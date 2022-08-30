import request from '@/utils/request'

/**
 * 发送短信
 */
export function sendSmsApi(phone) {
  return request({
    url: '/front/sms/send/' + phone,
    method: 'post'
  })
}

/**
 * 判断是否短信已否还存在
 */
export function checkSms(phone) {
  return request({
    url: '/front/sms/checkSmsSend/' + phone,
    method: 'get'
  })
}

/**
 * 验证手机验证码是否正确
 */
export function validate(data) {
  var dataValue = {}
  dataValue.code = data.code
  dataValue.phone = data.loginName
  return request({
    url: '/front/sms/validate',
    method: 'post',
    data: dataValue
  })
}

