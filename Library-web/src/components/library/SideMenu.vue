<template>
  <el-menu
    class="categories"
    default-active="0"
    @select="handleSelect"
    active-text-color="#DFCBE1">
    <el-menu-item class="navit" index="0"> 
      <i class="el-icon-thumb"></i>
      <span slot="title">全部</span>
    </el-menu-item>
    <el-menu-item class="navit"  v-for="item in catearr" :key="item" :index=item.cid>
      <i :class=item.icon></i>
      <span slot="title">{{item.cname}}</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
    name: 'SideMenu',
    data() {
      return {
        cid: '',
        catearr: []
      };
    },
    methods: {
      handleSelect (key, keyPath) {
        this.cid = key
        this.$emit('indexSelect')
      }
    },
    mounted: function () {
      var _this = this
      this.$axios.get("/category").then(resp=>{ 
        _this.catearr = resp.data
      })
    }
  }
</script>

<style scoped>
.categories {
  position: fixed;
  border-radius: 30px;
  margin-left: 44%;
  left: -600px;
  top: 100px;
  width: 150px;
  font-weight: bold;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}
.navit{
  margin-top: 10px;
  border-radius: 30px;
  margin-left: 10px;
  margin-right: 10px;
  margin-bottom: 10px;
}
</style>
