<template>
  <div class="admin" style="width: 1200px;margin: auto">

    <div style="display:flex">
        <div class="addcard" style="font-size:larger;">
            <div style="margin:20px">类别管理列表</div>
            <div style="margin:20px;background-color: #DFCBE1;color: #618cac;border-radius: 30px;padding: 5px;box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);text-align: center;"  @click="dialogFormVisibletrue">
                <i class="el-icon-s-check" style="margin-top:5px;margin-bottom: 5px;font-weight: bolder;">增加</i>
            </div>
        </div>
        <div class="searchcard">
            <el-input
            @keyup.enter.native="searchClick"
            placeholder="search by category-name.."
            prefix-icon="el-icon-search"
            size="small"
            style="width: 400px;"
            v-model="params">
            </el-input>
            <el-button size="small" type="primary" round="true" color="#DFCBE1" icon="el-icon-search" @click="load"></el-button>
        </div>
    </div>

    <div class="tablecard">
        <el-table :data="tableData" style="width: 100%;margin-bottom: 30px;" max-height="300">
            <el-table-column prop="cid" fixed label="类别号" width="150"></el-table-column>
            <el-table-column prop="cname" label="类名" width="300"></el-table-column>
            <el-table-column prop="icon" label="图标" width="300"></el-table-column>
            <el-table-column label="操作" fixed="right" width="300">
                <template v-slot="scope">
                <el-popconfirm
                    title="您确认删除这个类别吗？" style="margin-left: 10px"
                    @confirm="del(scope.row.cid)">
                    <el-button slot="reference" type="danger" >删除</el-button>
                </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </div>


    <el-dialog title="新增类别" :visible.sync="dialogFormVisible" style="width: 1300px">
      <el-form :model="form" style="width: 400px"  ref="ruleForm">
        <el-form-item label="类别名称" :label-width="'100px'">
          <el-input v-model="form.cname" ></el-input>
        </el-form-item>
        <el-form-item label="el图标名称" :label-width="'100px'">
          <el-input v-model="form.icon" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'mana_cate',
  data() {
    return {
      dialogFormVisible:false,
      tableData: [],
      params:'',
      form:{
        cname:'',
        icon: ''
      },
    }
  },

  mounted:function() {  //页面加载的时候要做什么
    this.load();
    console.log('cate:',this.tableData)
  },

  methods:{    //定义方法
    save(){
          this.$axios.post('/addcategory',this.form).then(res => {
            this.dialogFormVisible=false
            if(res.data.code === 200){
              this.$message({
                  type:"info",
                  message: "添加成功"
            }) 
              this.form={}//清空前台数据
              this.load()
            }else{
              this.$message({
                  type:"info",
                  message: "添加失败"
              }) 
            }
          })
        },

    load(){
      this.$axios.get('/category/'+this.params).then(res => {
        if(res.status === 200){
          this.tableData=res.data
        }
      })
    },

    dialogFormVisibletrue(){
      this.dialogFormVisible=true
    },

    del(cid){
      this.$axios.post(cid+'/deletecategory/').then(res =>{
        console.log(res)
        if(res.data.code===200){
          this.$message({
                  type:"info",
                  message: "删除成功"
                }) 
          this.load()
        }else{
          this.$message({
                  type:"info",
                  message: "删除失败"
          }) 
        }
      })
    },
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