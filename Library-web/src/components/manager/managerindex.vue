<template>
<el-container>
  <el-container>
    <el-aside width="220px">
        <div style="margin-right: 5px;margin-top: 50px;border-radius: 30px;padding:10px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);position:fixed;width: 200px;min-height:calc(100vh - 90px);">
        <el-menu :default-active="$route.path" router  active-text-color="#DFCBE1" style="border:none">
          <el-menu-item class="item" index="/manager/mana_home" >
            <i class="el-icon-s-cooperation"></i>
            首页
          </el-menu-item>
          <el-menu-item class="item" index="/manager/mana_user" >
            <i class="el-icon-s-custom"></i>
              用户列表
          </el-menu-item>
          <el-menu-item class="item" index="/manager/mana_books" >
            <i class="el-icon-s-management"></i>
              图书列表
          </el-menu-item>
          <el-menu-item class="item" index="/manager/mana_cate" >
            <i class="el-icon-s-flag"></i>
            图书类别
          </el-menu-item>
          <el-submenu  index="manage">
            <template slot="title" style="border-radius:30px">
              <i class="el-icon-platform-eleme"></i>
              管理
            </template>
            <el-menu-item index="/manager/mana_borrow" >
              <i class="el-icon-info"></i>
              还书管理
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </el-aside>

    <el-main>
        <div style="flex:1; height: 200px;min-height:calc(100vh - 90px);">
            <router-view/>
        </div>
    </el-main>

  </el-container>
</el-container>
</template>

<script>
export default {
  name: 'managerindex',
  mounted: function(){
    if(sessionStorage.getItem('isAdmin')=='false'){
      this.$router.push('/home')
    }
  }
}
</script>

<style scoped>
*{
    color: black;
}
.item{
  margin:20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 30px;
}
</style>