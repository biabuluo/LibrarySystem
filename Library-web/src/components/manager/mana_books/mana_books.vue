<template>
  <div class="admin" style="width: 1250px; font-weight: bolder">

    <div style="display:flex">
        <div class="addcard" style="font-size:larger;">
            <div style="margin:20px">图书管理列表</div>
            <div style="margin:20px;background-color: #DFCBE1;color: #618cac;border-radius: 30px;padding: 5px;box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);text-align: center;"  @click="dialogFormVisibletrue">
                <i class="el-icon-s-check" style="margin-top:5px;margin-bottom: 5px;font-weight: bolder;">增加</i>
            </div>
        </div>
        <div class="searchcard">
            <el-input
            @keyup.enter.native="searchClick"
            placeholder="search by bookname or author.."
            prefix-icon="el-icon-search"
            size="small"
            style="width: 400px;"
            v-model="params">
            </el-input>
            <el-button size="small" type="primary" round="true" color="#DFCBE1" icon="el-icon-search" @click="load"></el-button>
        </div>
    </div>

    <div class="tablecard">
        <el-table :data="tableData" style="width: 100%;margin-bottom: 30px;" max-height="400">
            <el-table-column fixed prop="iSBN" label="ISBN" width="100"></el-table-column>
            <el-table-column prop="title" label="书名" width="150"></el-table-column>
            <el-table-column prop="author" label="作者" width="100"></el-table-column>
            <el-table-column prop="publisher" label="出版社" width="200"></el-table-column>
            <el-table-column prop="publishdate" label="出版日期" width="200"></el-table-column>
            <el-table-column prop="location" label="位置" width="150"></el-table-column>
            <el-table-column prop="category.cname" label="类别" width=100></el-table-column>
            <el-table-column prop="nums" label="数量" width="50"></el-table-column>
            <el-table-column prop="cover" label="封面" width="300" align="center">
              <template v-slot="scope">
                <img :src="scope.row.cover" width="140" style="border-radius: 30px"/>
              </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" width="150">
              <template v-slot="scope">
                <el-button type="primary" size="small" round @click="dialogFormVisibletrue2(scope.row.bid)">编辑</el-button>
                <el-popconfirm
                    title="您确认删除此书信息吗？" style="margin-left: 10px"
                    @confirm="del(scope.row.bid)">
                  <el-button slot="reference" size="small" round type="danger">删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
        </el-table>
    </div>

<!-- add -->
    <el-dialog title="新增图书" :visible.sync="dialogFormVisible" width="50%" style="margin-left: 10px" top="100px">
      <el-form ref="ruleForm" :model="form" :rules="rules" label-width="80px" :inline="true" style="width: 60%;margin-top: -20px">
        <el-form-item label="封面的url" width="100" prop="cover" >
          <el-input v-model="form.cover" placeholder="请输入封面的url"> </el-input>
        </el-form-item>
        <el-form-item label="书名" width="100" prop="title" >
          <el-input v-model="form.title" placeholder="请输入书名"> </el-input>
        </el-form-item>
        <el-form-item label="作者" width="100" prop="author" >
          <el-input v-model="form.author" placeholder="请输入作者"> </el-input>
        </el-form-item>
        <el-form-item label="isbn" width="100" prop="isbn" >
          <el-input v-model="form.iSBN" placeholder="请输入ISBN"> </el-input>
        </el-form-item>
        <el-form-item label="出版日期" prop="publishDate">
          <el-input v-model="form.publishdate" placeholder="请输入出版日期"></el-input>
        </el-form-item>
        <el-form-item label="出版社" prop="publisher">
          <el-input v-model="form.publisher" placeholder="请输入出版社"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="abs">
          <el-input v-model="form.abs" placeholder="请输入简介"></el-input>
        </el-form-item>
        <el-form-item label="类别" prop="cid">
          <el-select v-model="form.cid" placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.cid"
                :label="item.cname"
                :value="item.cid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入位置"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="nums">
          <el-input-number v-model="form.nums"  :min="0"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改图书" :visible.sync="dialogFormVisible2" width="50%" style="margin-left: 10px" top="100px">
      <el-form ref="ruleForm2" :model="form2" :rules="rules" label-width="80px" :inline="true" style="width: 60%;margin-top: -20px">
        <el-form-item label="封面的url" width="100" prop="cover" >
          <el-input v-model="form2.cover" placeholder="请输入封面的url"> </el-input>
        </el-form-item>
        <el-form-item label="书名" width="100" prop="title" >
          <el-input v-model="form2.title" placeholder="请输入书名"> </el-input>
        </el-form-item>
        <el-form-item label="作者" width="100" prop="author" >
          <el-input v-model="form2.author" placeholder="请输入作者"> </el-input>
        </el-form-item>
        <el-form-item label="isbn" width="100" prop="isbn" >
          <el-input v-model="form2.iSBN" placeholder="请输入ISBN"> </el-input>
        </el-form-item>
        <el-form-item label="出版日期" prop="publishDate">
          <el-input v-model="form2.publishdate" placeholder="请输入出版日期"></el-input>
        </el-form-item>
        <el-form-item label="出版社" prop="publisher">
          <el-input v-model="form2.publisher" placeholder="请输入出版社"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="abs">
          <el-input v-model="form2.abs" placeholder="请输入简介"></el-input>
        </el-form-item>
        <el-form-item label="类别" prop="cid">
          <el-select v-model="form2.cid" placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.cid"
                :label="item.cname"
                :value="item.cid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form2.location" placeholder="请输入位置"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="nums">
          <el-input-number v-model="form2.nums"  :min="0"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'mana_books',
  data() {
    return {
      dialogFormVisible:false,
      dialogFormVisible2:false,
      tableData: [],
      params:'',
      form:{},
      form2:{},
      rules: {
        title: [
          { required: true, message: '请输入书名', trigger: 'blur' },
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        author: [
          { required: true, message: '请输入作者', trigger: 'blur' }
        ],
        isbn: [
          { required: true, message: '请输入ISBN', trigger: 'blur' }
        ],
        publishDate: [
          {  message: '请输入出版日期', trigger: 'blur',required:'true'}
        ],
        publisher: [
          {  trigger: 'blur',message: '请输入出版社',required:'true' }
        ],
        location:[
          {trigger: 'blur',message: '请输入位置',required:'true'}
        ],
        cid:[
          {trigger: 'blur',message: '请选择类别',required:'true'}
        ]
      },
      options:[],
      value:''
    }
  },
  created() {  //页面加载的时候要做什么
    this.load();
  },
  methods:{    //定义方法
    dialogFormVisibletrue(){
      this.dialogFormVisible=true
      this.$axios.get('/category').then(res =>{
        this.options=res.data
      })
    },
    dialogFormVisibletrue2(bid){
      this.dialogFormVisible2=true
      this.$axios.get('/category').then(res =>{
        this.options=res.data
      })
      //获取当前书本数据
      this.$axios.get('/searchbook/'+bid).then(res=>{
        this.form2 = res.data
      })
    },
    
    save(){
          this.$axios.post('/addbook/'+this.form.cid,this.form).then(res => {
            if(res.status == 200){
              this.$message({
                  type:"info",
                  message: "添加成功"
              }) 
              this.form={}//清空前台数据
            }else{
              this.$message({
                  type:"info",
                  message: "添加失败"
              }) 
            }
        })
      this.dialogFormVisible=false
      this.load()
    },

    update() {
          this.$axios.post('/addbook/'+this.form2.cid,this.form2).then(res =>{
            if(res.status === 200){
              this.$message({
                  type:"info",
                  message: "修改成功"
              }) 
              this.form={}
              this.$router.push('/booklist')
            }else{}
            this.$message({
                  type:"info",
                  message: "修改失败"
              }) 
          })
          this.dialogFormVisible=false
      this.load()
    },

    load(){
      this.$axios.get('/searchBook?keyword='+
        this.params
      ).then(res => {
        if(res.status === 200){
          this.tableData=res.data
        }
      })
    },

    del(id){
      this.$axios.post('/deletebook/'+id).then(res =>{
        if(res.data.code==200){
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
    }
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