<template>
  <div class="admin" style="width: 1200px;margin:auto">

    <div style="display:flex">
        <div class="addcard" style="font-size:larger;">
            <div style="margin:20px">借阅管理列表</div>
        </div>
        <div class="searchcard">
            <el-input
            @keyup.enter.native="searchClick"
            placeholder="search by uid.."
            prefix-icon="el-icon-search"
            size="small"
            style="width: 400px;margin-bottom:20px;margin-right: 760px;"
            v-model="params.username">
            </el-input>
            <el-input
            @keyup.enter.native="searchClick"
            placeholder="search by isbn.."
            prefix-icon="el-icon-search"
            size="small"
            style="width: 400px;"
            v-model="params.isbn">
            </el-input>
            <el-button size="small" type="primary" round="true" color="#DFCBE1" icon="el-icon-search" @click="load"></el-button>
        </div>
    </div>


    <div class="tablecard">
        <el-table :data="tableData" style="width: 100%;margin-bottom: 30px;" max-height="400">
            <el-table-column prop="isbn" label="ISBN" fixed width="150"></el-table-column>
            <el-table-column prop="name" label="姓名" width="100"></el-table-column>
            <el-table-column prop="title" label="书名"  width="150"></el-table-column>
            <el-table-column prop="username" label="学号" width="100"></el-table-column>
            <el-table-column prop="borrowdate" label="借阅日期" width="150"></el-table-column>
            <el-table-column prop="daysUsed" label="已借天数" width="100"></el-table-column>
            <el-table-column label="操作" fixed="right" width="100">
                <template v-slot="scope">
        <!--scope.row就是当前行数据-->
                <el-popconfirm
                    title="确认删除这个借书记录吗？" style="margin-left: 10px"
                    @confirm="del(scope.row.bid,scope.row.uid)">
                    <el-button slot="reference" type="primary">还书</el-button>
                </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'mana_borrow',
  data() {
    return {
      dialogFormVisible:false,
      tableData: [],
      params:{
        username:'',
        isbn:''
      },
    }
  },
  created() {  //页面加载的时候要做什么
    this.load();
  },
  methods:{    //定义方法
    load(){
      this.$axios.get('/searchborrow?username='+this.params.username+'&isbn='+this.params.isbn)
      .then(res => {
        console.log('borrow',res)
        if(res.status === 200){
          this.tableData=res.data
          this.$message({
                  type:"info",
                  message: "搜索成功"
              }) 
        }
      })
    },
    del(bid,uid){
      console.log('bid',bid)
      console.log('uid',uid)
      this.$axios.post('/deleteborrow?bid='+bid+'&uid='+uid).then(res =>{
        console.log(res)
        if(res.data.code===200){
          this.$message({
                  type:"info",
                  message: "还书成功"
              }) 
          this.load()
        }else{
          this.$message({
                  type:"info",
                  message: "还书失败"
              }) 
        }

      })
    },
    //tast
    // func(bid,uid){
    //   console.log('bid',bid)
    //   console.log('uid',uid)
    // }
  }
}
</script>

<style scoped>
.searchcard{
    margin: 20px;
    border-radius: 30px;
    box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 30px;
    width: 600px;
}
.addcard{
    margin: 20px;
    margin-right: 160px;
    border-radius: 30px;
    box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 30px;
}
.tablecard{
    margin: 20px;
    margin-right: 100px;
    border-radius: 30px;
    box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 30px;
}
</style>