<template>
<el-container>
  <el-aside width="400px">
    <el-switch class="switch" v-model="isCollapse" active-color="#618cac" inactive-color="#DFCBE1"></el-switch>
    <el-menu default-active="1" 
      class="el-menu-vertical-demo" 
      text-color="#222"
      active-text-color="#DFCBE1"
      @open="handleOpen" @close="handleClose" :collapse="isCollapse">
      <el-menu-item class="item"  @click="drawer = true" index="1">
        <i class="el-icon-user-solid"></i>
        <span slot="title">关于我们</span>
        <el-drawer
          title="来自暨大软工"
          :visible.sync="drawer"
          :direction="direction"
          :before-close="handleClose">
        <span>我来啦!</span>
        </el-drawer>
      </el-menu-item>
      <el-menu-item class="item" index="2">
        <i class="el-icon-star-on"></i>
        <span slot="title">推荐图书</span>
      </el-menu-item>
      <el-menu-item class="item" index="3">
        <i class="el-icon-circle-plus"></i>
        <span slot="title">新增图书</span>
      </el-menu-item>
      <el-menu-item class="item" index="4">
        <i class="el-icon-question"></i>
        <span slot="title">其它信息</span>
      </el-menu-item>
    </el-menu>
    <button class="btn" @click="show2=!show2">一张图看懂如何借书？</button>
    <transition name="el-zoom-in-top">
      <div v-show="show2" class="steps" style="height: 300px; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
        <el-steps direction="vertical" :active="1">
          <el-step title="步骤 1" description="找到自己喜欢的书"></el-step>
          <el-step title="步骤 2" description="登录用户"></el-step>
          <el-step title="步骤 3" description="借阅图书"></el-step>
        </el-steps>
      </div>  
    </transition>
  </el-aside>
  <el-container>
    <el-header height="250px">
      <el-carousel :interval="2000" type="card" height="200px">
      <el-carousel-item v-for="item in items" :key="item.id">
      <a :href=item.link target="_blank">
        <img :src=item.img alt="" class="carousel_img" style="width:100%;height: auto;">
      </a>
      <h3>{{item.title}}</h3>
    </el-carousel-item>
  </el-carousel>
    </el-header>
    <el-main>
      <el-calendar v-model="value">
      </el-calendar>
    </el-main>
  </el-container>
</el-container>
</template>

<script>
export default {
  name: 'AppIndex',
  data() {
    return {
      isCollapse: false,
      drawer: false,
      direction: 'rtl',
      value: new Date(),
      show2:true,
      items: [{
          id: 1,
          title: 'How2J.cn - Java 全栈学习网站',
          img: '../../../static/img/carousel/genshin3.jpg',
          link: 'http://how2j.cn?p=50613'},
        {
          id: 2,
          title: 'Vue.js - 渐进式 JavaScript 框架',
          img: '../../../static/img/carousel/genshin4.jpg',
          link: 'https://cn.vuejs.org/'
        },
        {
          id: 3,
          title: 'element-ui - 网站快速成型工具',
          img: '../../../static/img/carousel/genshin.jpg',
          link: 'http://element-cn.eleme.io/#/zh-CN'
        }]
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    }
  }
}
</script>

<style scoped>
*{
  color: #618cac;
  font-weight: bolder;
}
.btn {
  margin-top: 30px;
  margin-left: 40px;
  background: white;
  /* 给按钮添加圆角 */
  -webkit-border-radius: 28;
  -moz-border-radius: 28;
  border-radius: 28px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  font-family: Arial;
  color: #222;
  font-size: 15px;
  padding: 19px;
  text-decoration: none;
  border: none;
}
/* 悬停样式 */
.btn:hover {
  background: #DFCBE1;
  text-decoration: none;
}
/* .how{
  margin-top: 30px;
} */
.steps{
  margin-top: 30px;
  margin-left: 10px;
  margin-right: 10px;
  padding: 20px;
  border-radius: 30px;
}
.switch{
  margin-left: 40px;
  margin-bottom: 5px;
  margin-top: 20px;
}
.item{
  margin: 10px;
  border-radius: 30px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  margin-top: 30px;
  margin-left: 15px;
  margin-bottom: 10px;
  width: 200px;
  min-height: 200px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 30px;
}
.el-menu-vertical-demo{
  margin-top: 30px;
  margin-left: 15px;
  margin-bottom: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 30px;
}
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
