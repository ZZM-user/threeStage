// 验证手机号  https://blog.csdn.net/u013544734/article/details/51917036
var phoneFunction = (rule, value, callback) => {
    const exp= /^0?(13|15|17|18|14|19)[0-9]{9}$/
    if (value === '') {
      callback(new Error('请输入账号'));
    } else {
      //判断是否是手机号
      if(exp.test(value)){
        callback();
      }else{
       callback(new Error('账号必须是手机号，格式不正确'));
      }
    }
  };

 
// 暴露对象
export default {
  phone : phoneFunction 
}
