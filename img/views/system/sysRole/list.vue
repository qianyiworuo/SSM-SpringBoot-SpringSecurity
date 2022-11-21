<template>
  <div class="app-container">
    <!--查询表单-->
<div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="24">
            <el-form-item label="角色名称">
              <el-input style="width: 100%" v-model="searchObj.roleName" placeholder="角色名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="display:flex">
          <el-button type="primary" icon="el-icon-search" size="mini"  @click="fetchData()">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
        </el-row>
      </el-form>
    </div>
    <!-- 工具条 -->
    <div class="tools-div">
      <el-button type="success" icon="el-icon-plus" size="mini" @click="add">添 加</el-button>
      <el-button class="btn-add" size="mini" @click="batchRemove()" >批量删除</el-button>
    </div>
     <!--表格渲染
     表格 -->  
   <el-table
     v-loading="listLoading"
     :data="list"
     stripe
     border
     style="width: 100%;margin-top: 10px;"
     @selection-change="handleSelectionChange">
     <el-table-column type="selection"/>
     <el-table-column
       label="序号"
       width="70"
       align="center">
       <template slot-scope="scope">
         {{ (page - 1) * limit + scope.$index + 1 }}
       </template>
     </el-table-column>
     <el-table-column prop="roleName" label="角色名称" />
     <el-table-column prop="roleCode" label="角色编码" />
     <el-table-column prop="createTime" label="创建时间" width="160"/>
     <el-table-column label="操作" width="200" align="center">
       <template slot-scope="scope">
         <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="修改"/>
         <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)" title="删除"/>
       </template>
     </el-table-column>
   </el-table>
     <!--分页组件-->
 <el-pagination
   :current-page="page"
   :total="total"
   :page-size="limit"
   style="padding: 30px 0; text-align: center;"
   layout="total, prev, pager, next, jumper"
   @current-change="fetchData"
 />
 <!--定义弹出层-->
 <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%" >
      <el-form ref="dataForm" :model="sysRole" label-width="150px" size="small" style="padding-right: 40px;">
        <el-form-item label="角色名称">
          <el-input v-model="sysRole.roleName"/>
        </el-form-item>
        <el-form-item label="角色编码">
          <el-input v-model="sysRole.roleCode"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
</el-dialog>
   </div>
   
</template>
 <script>
 //引入api接口文件
   import api from '@/api/system/sysRole'

   const defaultForm =  {
    id: '',
    roleName: '',
    roleCode: ''
   }

   export default {
        // 定义数据模型
       data() {
           return {
               listLoading:true, // 数据是否正在加载
               list: [], // 角色列表
               total: 0, // 总记录数
               page: 1, // 页码
               limit: 4, // 每页记录数
               searchObj: {}, // 查询条件

               dialogVisible: false,
               sysRole: defaultForm,
               saveBtnDisabled: false,

               selectValue: []//复选框选择的内容封装的数组
           }
       },
        // 页面渲染之前获取数据
       created() {
           this.fetchData()
       },
        // 定义方法
        methods: {
           //列表方法
           fetchData(pageNum=1) {
               this.page = pageNum
               //调用api，Ajax
               api.getPageList(this.page, this.limit, this.searchObj).then(Response => {
                   //console.log(Response)
                   //debugger
                   this.listLoading = false
                   this.list = Response.data.records
                   this.total = Response.data.total
               })
           },
            // 重置表单
           resetData() {
            console.log('重置查询表单')
            //清空表单
            this.searchObj = {}
            //查询所有数据
            this.fetchData()
           },
           removeDataById(id) {
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
              }).then(() => {
                  api.removeId(id).then(Response =>{  
                    this.$message({
                    type: 'success',
                    message: '删除成功!' 
                    })
                    this.fetchData(this.page)
                  })
                }).catch(() => {
                    this.$message({
                    type: 'info',
                    message: '已取消删除'
                    })      
                  })
            },
            //弹出添加的表单
            add(){
              this.dialogVisible = true
              this.sysRole = {}
            },
            //添加或更新
            saveOrUpdate(){
              if(!this.sysRole.id){
                this.save()
              }else{
                this.update()
              }
            },
            //添加
            save(){
              api.saveRole(this.sysRole).then(Response =>{
                //console.log(Response)
                //this.message.success(Response.message || '操作成功')
                this.$message({
                    type: 'success',
                    message: '新增成功!' 
                    })
                this.dialogVisible = false
                this.fetchData(this.page)
              })
            },
            //回显数据
            edit(id){
              this.dialogVisible = true
              api.findRoleById(id).then(Response => {
                //console.log(Response)
                //this.sysRole.id = Response.data.id
                //this.sysRole.roleName = Response.data.roleName
                //this.sysRole.roleCode = Response.data.roleCode
                this.sysRole = Response.data
              })
            },
            update(){
              api.updateById(this.sysRole).then(Response =>{
                //this.message.success(Response.message || '操作成功')
                this.$message({
                    type: 'success',
                    message: '修改成功!' 
                    })
                this.dialogVisible = false
                this.fetchData(this.page)
              })
            },
            //获取复选框数据
            handleSelectionChange(selection){
              this.selectValue = selection
              //console.log(selection)

            },
            //批量删除
            batchRemove(){
              if(this.selectValue.length == 0){
                this.$message.warning('请选择要删除的记录！')
                return
              }else{
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                  var idList = []
                  for(var i = 0; i < this.selectValue.length; i++){
                    var id = this.selectValue[i].id
                    //取到的id值，放进数组。
                    idList.push(id)
                  }
                  api.batchRemove(idList).then(Response =>{  
                    this.$message({
                    type: 'success',
                    message: '删除成功!' 
                    })
                    this.fetchData(this.page)
                  })
                }).catch(() => {
                    this.$message({
                    type: 'info',
                    message: '已取消删除'
                    })      
                  })
              }

            }





          }      
      }
 </script>

