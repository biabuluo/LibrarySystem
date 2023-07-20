<template>
  <el-menu
    class="nav"
    :default-active="'/index'"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="#DFCBE1"
    style="min-width: 1300px">
    <el-menu-item class="navit" v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>
    <el-menu-item class="navit" index="/manager/mana_home" :disabled=this.limit_val>
    管理中心
    </el-menu-item>
    <!-- <router-link to="/login"> -->
      <a  @click="open1" style="color: #222;float: right;padding: 20px">login out</a>
      <i class="el-icon-switch-button" style="float:right;font-size: 40px;font-weight: bolder;color: #DFCBE1;padding-top: 8px"></i>
    <!-- </router-link> -->
    <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold;color: #222;">没书读图书馆</span>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      limit_val:null,
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/jotter', navItem: '书评'},
        {name: '/library', navItem: '图书馆'},
        {name: '/admin', navItem: '个人中心'},
      ]
    }
  },
  mounted:function(){
    if(sessionStorage.getItem('isAdmin')==='true'){
      console.log("isadmin = true")
      this.limit_val = false
    }else{
      console.log("isadmin = flase")
      this.limit_val = true
    }
    console.log(this.limit_val)
    console.log(sessionStorage.getItem('isAdmin'))
  },

  methods:{
    open1() {
      const h = this.$createElement;
      this.$notify({
        title: '系统信息',
        message: h('i', { style: 'color: teal'}, '用户已登出')
      });
      sessionStorage.clear()
      this.$router.push('/login')
    },
  }
}
</script>

<style scoped>
.nav{
  border-radius: 30px;
  margin: 10px;
}
.navit{
  margin-left: 20px;
}
a{
  text-decoration: none;
}

span {
  pointer-events: none;
}
</style>
