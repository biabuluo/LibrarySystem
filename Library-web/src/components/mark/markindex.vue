<template>
<el-container>
  <el-aside width="200px">
      <i class="el-icon-chat-line-round"  style="font-size: 100px" @click="show = !show"></i>
      <div  class="commbox">
        <transition name="el-fade-in-linear">
          <div v-show="show"  class="transition-box" @click="addcomment()">留下足迹吧</div>
        </transition>
      </div>
      <edit-form ref="edit"></edit-form>
  </el-aside>
  <el-main>
    <div style="margin-top: 0px">
      <span class="slogan" style="text-align:center;margin-left: 500px;">不妨看看吧<i class="el-icon-s-comment"></i></span>
      <el-divider content-position="right">来自喜欢书本的我们</el-divider>
    </div>
    <div class="comments">
      <div class="block">
        <el-timeline>
          <el-timeline-item v-for="item in commenttable" :key="item" :timestamp=item.commdate color="#DFCBE1" placement="top">
            <el-card class="card">
              <div class="in" style="margin: 0px;padding: 0%;height: 0px;">
                <el-avatar :src="item.img" style="margin-left: 0%;margin-top: 0px;"></el-avatar>
             </div>
              <div class="in">
                <div style="font-size:20px; margin: 0px;padding: 0%;margin-top: 0px;width: 1000px">{{item.nick}}</div>
              </div>
              <div class="comment">
                <div style="margin-left:0px;margin-top:0px;height: 5px;">To {{item.title}}：</div>
              </div>
              <div class="comment">
                <p>{{item.review}}</p>
              </div>
              <div class="star_level">
                <el-rate
                  v-model="item.level"
                  disabled
                  show-score
                  text-color="#ff9900">
                </el-rate>
              </div>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
    </div>
  </el-main>
</el-container>
</template>

<script>
import EditForm from './EditForm'
export default {
  name: 'markindex',
  components:{
    EditForm
  },
  data() {
    return {
      value:null,
      show :false,
      commenttable:[]
    };
  },
  mounted : function () {
    var _this = this
    this.$axios.get("/comment").then(resp=>{
      _this.commenttable = resp.data.reverse()
    })
  },

  methods:{
    addcomment(){
      this.$refs.edit.dialogFormVisible = true
    },
  }
}
</script>

<style scoped>
*{
  color: black;
  margin: 20px;
}
.star_level{
  margin-left: 530px;
}
.in{
  display: inline-block;
  vertical-align: top;
  width: 5%;
}
/* .comment{
  display: inline-block;
  vertical-align: top;
} */
.commbox{
   position:fixed;
   margin-top: 300px; 
   height: 20px;
   margin-left: 10px;
}
.el-icon-chat-line-round{
  position: fixed;
  margin-top: 200px;
  margin-left: 30px;
}
.card{
  border-radius: 30px;
}
.slogan{
  font-size: larger;
}
.comments{
  margin-top: 30px;
}
.transition-box {
    margin-bottom: 10px;
    width: 200px;
    height: 100px;
    border-radius: 30px;
    box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);
    background-color: #DFCBE1;
    text-align: center;
    color: #fff;
    padding: 40px 20px;
    box-sizing: border-box;
    margin-right: 20px;
  }
</style>