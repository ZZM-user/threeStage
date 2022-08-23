// 管理员权限,不是就删除节点
import store from '@/store'

export default {
  inserted(el) {
    const isAdmin = store.getters.loginType === 1
    if (!isAdmin) {
      if (el.parentNode) {
        el.parentNode.removeChild(el)
      }
    }
  }
}
