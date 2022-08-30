/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
}


/**
 * 验证手机号是否可用
 * @param {Object} value
 */
export function isPhone(value){
  const exp= /^0?(13|15|17|18|14|19)[0-9]{9}$/
  // 判断是否是手机号
  return exp.test(value)
}
