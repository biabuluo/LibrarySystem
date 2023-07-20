<template>
<el-container>
  <el-container>
    <el-aside width="500px">
        <body>
            <div class="card">
                <div class="imgBx">
                    <img :src=user.img>
                </div>
                <div class="content">
                <div class="details">
                    <h2>{{user.nick}}<br><span>{{this.role}}</span></h2>
                    <div class="data">
                        <h3>学号<br><span>{{user.username}}</span></h3>
                        <h3>姓名<br><span>{{user.name}}</span></h3>
                        <h3>借阅图书<br><span>{{user.borrownums}}</span></h3>
                    </div>
                    <div class="actionBtn">
                        <h3>motto:<br><span>{{user.motto}}</span></h3>    
                    </div>
                </div>
                </div>
            </div>
        </body>
    </el-aside>
    <el-main>
        <div class="title" style="text-align:center;margin-top: 20px;color: #DFCBE1;" @click="log"> 用户借阅的书籍</div>
        <div class="card2">
            <el-table :data="tableData" style="width: 100%;margin-top: 20px;" max-height="300">
                <el-table-column fixed prop="borrowdate" label="借阅日期" width="150"></el-table-column>
                <el-table-column prop="name" label="姓名" width="120"></el-table-column>
                <el-table-column prop="username" label="学号" width="120"></el-table-column>
                <el-table-column prop="title" label="书名" width="120"></el-table-column>
                <el-table-column prop="isbn" label="ISBN" width="120"></el-table-column>
                <el-table-column prop="daysUsed" label="已借天数" width="300"></el-table-column>
            </el-table>
        </div>
        <div class="title" style="text-align:center;margin-top: 20px;color: #618cac;"> 用户留下的足迹</div>
        <div class="card2">
            <el-table :data="commentdata" style="width: 100%;margin-top: 20px;" max-height="300">
                <el-table-column fixed prop="commdate" label="日期" width="150"></el-table-column>
                <el-table-column prop="nick" label="昵称" width="120"></el-table-column>
                <el-table-column prop="username" label="学号" width="120"></el-table-column>
                <el-table-column prop="title" label="书名" width="120"></el-table-column>
                <el-table-column prop="review" label="评论" width="300"></el-table-column>
            </el-table>
        </div>
    </el-main>
  </el-container>
</el-container>
</template>

<script>
import { onMounted } from 'vue';
export default {
  name: 'personindex',
  data() {
    return{
        role : '',
        uid : JSON.parse(sessionStorage.getItem("uid")),
        user : null,
        tableData: [],
        commentdata:[],
    }
  },
  mounted: function () {
    var  _this = this
    this.$axios.get('/' + this.uid + '/borrow_view').then(resp => {
         _this.tableData = resp.data
      })

    this.$axios.get('/' + this.uid + '/user_info').then(resp => {
        _this.user = resp.data
    })

    this.$axios.get('/' + this.uid + '/comment').then(resp => {
        _this.commentdata = resp.data
    })
    if (JSON.parse(sessionStorage.getItem("isAdmin"))===true){
      this.role = '管理员'
    }else{
      this.role = '普通用户'
    }
},

  methods: {
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    log() {
      console.log(this.role)
      console.log(this.user)
      console.log(JSON.parse(sessionStorage.getItem("isAdmin")))
    }
  },
}

</script>

<style scoped>
*{
    color: black;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}
.card2{
    border-radius: 30px;
    box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 20px;
    margin: 20px;
}
body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  position:fixed;
}

.card {
  position: relative;
  margin-left: 40px;
  width: 350px;
  height: 190px;
  /* height: 450px; */
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 35px 80px rgba(0, 0, 0, 0.15);
  transition: 0.5s;
}

.card:hover {
  height: 450px;
}

.imgBx {
  position: absolute;
  left: 50%;
  top: -50px;
  transform: translateX(-50%);
  width: 150px;
  height: 150px;
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 15px 50px rgba(0, 0, 0, 0.35);
  overflow: hidden;
  transition: 0.5s;
}

.card:hover .imgBx {
  width: 250px;
  height: 250px;
}

.imgBx img{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.card .content {
  position: absolute;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  overflow: hidden;
  /* margin-top: -15px; */
}

.card .content .details {
  padding: 40px;
  text-align: center;
  width: 100%;
  transition: 0.5s;
  transform: translateY(150px);
}

.card:hover .content .details {
  transform: translateY(0px);
}

.card .content .details h2 {
  font-size: 1.25em;
  font-weight: 600;
  color: #555;
  line-height: 1.2em;
}

.card .content .details h2 span {
  font-size: 0.75em;
  font-weight: 500;
  opacity: 0.5;
}

.card .content .details .data {
  display: flex;
  justify-content: space-between;
  margin: 20px 0;
}

.card .content .details .data h3 {
  font-size: 1em;
  color: #555;
  line-height: 1.2em;
  font-weight: 600;
}

.card .content .details .data h3 span {
  font-size: 0.85em;
  font-weight: 400;
  opacity: 0.5;
}

.card .content .details .actionBtn {
  display: flex;
  justify-content: space-between;
  box-shadow:  0 15px 50px rgba(0, 0, 0, 0.35);
  padding: 5px;
  border-radius: 20px;
  background-color: #fff;
}

.card .content .details .actionBtn h3 {
  text-align: left;
  font-size: 1em;
  color: #618cac;
  line-height: 1.2em;
  font-weight: 600;
  margin-left: 10px;
}
.card .content .details .actionBtn h3 span{
  font-size: 0.85em;
  font-weight: 400;
  opacity: 0.5;
}

</style>