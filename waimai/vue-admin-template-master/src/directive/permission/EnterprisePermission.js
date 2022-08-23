// 商家权限,不是就删除节点
import store from '@/store'

export default {
  inserted(el) {
    const isAdmin = store.getters.loginType === 2
    if (!isAdmin) {
      if (el.parentNode) {
        el.parentNode.removeChild(el)
      }
    }
  }
}
