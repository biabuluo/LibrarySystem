<template>
  <body id="poster">
    <div class="container">
      <h1>没书读图书馆</h1>
      <div class="form-control">
        <input type="text" required v-model="loginForm.username">
        <label>
          <span style="transition-delay: 0ms">u</span><span style="transition-delay: 50ms">s</span><span style="transition-delay: 100ms">e</span><span style="transition-delay: 150ms">r</span><span style="transition-delay: 200ms">n</span><span style="transition-delay: 250ms">a</span><span style="transition-delay: 300ms">m</span><span style="transition-delay: 350ms">e</span>
        </label>
      </div>
      <div class="form-control">
        <input type="password" required v-model="loginForm.password">
        <label>
          <span style="transition-delay: 0ms">p</span><span style="transition-delay: 50ms">a</span><span style="transition-delay: 100ms">s</span><span style="transition-delay: 150ms">s</span><span style="transition-delay: 200ms">w</span><span style="transition-delay: 250ms">o</span><span style="transition-delay: 300ms">r</span><span style="transition-delay: 350ms">d</span>
        </label>
      </div>
      <button class="btn" v-on:click="login">login</button>
      <p class="text">no account?<a href="#"> 麻溜点！</a></p>
    </div>
  </body>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      var _this = this
      console.log(this.$store.state)
      this.$axios
        .post('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            // var data = this.loginForm
            _this.$store.commit('login', _this.loginForm)
            var path = this.$route.query.redirect
            this.$router.replace({
              path: path === '/' || path === undefined ? '/index' : path
            })
            this.$message({
              type:"info",
              message: "登陆成功"
            })            
            sessionStorage.setItem('uid', JSON.stringify(successResponse.data.data.uid))
            sessionStorage.setItem('isAdmin', JSON.stringify(successResponse.data.data.isAdmin))
            // console.log( sessionStorage.getItem('isAdmin'))
          } else {
            this.$message({
              type:"info",
              message: "登陆失败：账号信息错误"
            })
          }
        })
        .catch(failResponse => {
        })
    }
    }

  }

</script>
<style scoped>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body{
  background: url("https://w.wallhaven.cc/full/j3/wallhaven-j3dyoq.jpg")no-repeat center;
  background-attachment: fixed;
  /* background-color: steelblue; */
  color: white;
  font-family: sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0px;
}
/* #poster {
    background:url("../assets/library.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  } */
.container{
  background-color: rgb(0,0,0,0.3);
  padding: 20px 40px ;
  border-radius: 20px;
  box-shadow: 0 0 25px #cac6c6;
}
.container h1{
  text-align: center;
  margin-bottom: 30px;
}
.btn{
  cursor: pointer;
  width: 100%;
  background-color: white;
  padding: 15px;
  border: 0;
  font-size: 16px;
  font-family: inherit;
  font-weight: bolder;
  border-radius: 20px;
  box-shadow: 0 0 25px #cac6c6;
}
.btn:focus{
  outline:0;
}
.btn:active{
  transform:scale(0.98);
}
.container a{
  text-decoration: none;
  color: lightblue;
  font-weight: bold;
}
.text{
  margin-top: 30px;
}
.form-control{
  width: 300px;
  margin: 20px 0 40px;
  position: relative;
}
.form-control input{
  background-color: transparent;
  border: 0;
  border-bottom: 2px solid white;
  display: block;
  width: 100%;
  padding: 15px 0;
  font-size: 18px;
  color: white;
}
.form-control input:focus{
  outline: 0;
  border-bottom-color: lightblue;
}
.form-control input:valid{
  border-bottom-color: black;
}
.form-control label{
  position: absolute;
  left: 0;
  top: 15px;
}
.form-control label span{
  display: inline-block;
  font-size: 18px;
  transition: all 0.3s cubic-bezier(0.68,-0.55,0.265,1.55);
}
.form-control input:valid + label span,
.form-control input:focus + label span{
  transform: translateY(-30px);
  color: lightblue;
}
</style>
