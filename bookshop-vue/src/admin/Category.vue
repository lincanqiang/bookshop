<template>
    <div>
        <div>
            <el-dialog title="分类编辑"
                       :visible.sync="dialogFormVisible"
                       center
                       width="20%" >
                <el-form ref="form"
                        :model="form">
                    <el-form-item>
                        <el-tag v-model="form.id"
                                v-show="false"/>
                    </el-form-item>
                    <el-form-item label="分类图片">
                        <el-upload
                                action=""
                                auto-upload="false"
                                :http-request="handleFile"
                                :multiple="false"
                                :limit="1"
                                :on-exceed="handleExceed"
                                :file-list="fileList">
                            <el-button size="small" type="primary">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="分类名称">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="handleAdd">确 定</el-button>
                    <el-button @click="handleCancel">取 消</el-button>
                </div>
            </el-dialog>
            <el-button
                    type="primary"
                    icon="el-icon-circle-plus-outline"
                    style="margin-bottom: 30px"
                    @click="handleOpen">新增分类
            </el-button>
        </div>
        <div>
            <el-table :data="category">
                <el-table-column
                        prop="id"
                        label="分类ID"/>
                <el-table-column
                        prop="name"
                        label="分类名称"/>
                <el-table-column
                    prop="image"
                    label="分类图片">
                    <template slot-scope="scope">
                    <el-image
                            style="width: 100px; height: 100px"
                            :src="scope.row.image"
                            fit="fill"/>
                    </template>
                </el-table-column>
                <el-table-column
                        label="操作">
                    <template slot-scope="scope">
                        <el-button
                                type="primary"
                                icon="el-icon-edit"
                                @click="handleEdit(scope.$index,scope.row)">编辑
                        </el-button>
                        <el-button
                                type="danger"
                                icon="el-icon-delete"
                                @click="handleDelete(scope.$index,scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div>
            <el-col :span="24">
                <el-pagination
                        background
                        layout="total,prev,pager,next"
                        :page-size="5"
                        :pager-count="5"
                        @current-change="handleCurrentChange"
                        :total="total"
                        :current-page="pageNum"
                />
            </el-col>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Category",
        data(){
            return{
                uri:'categories',
                total:0,
                pageNum:0,
                category:[],
                dialogFormVisible:false,
                form:{
                    id:'',
                    name:''
                },
                fileList:[]
            }
        },

        created() {
            this.list(1);
        },

        methods: {
            list(pageNum) {
                let url = this.uri + "?pageNum=" + pageNum;
                this.axios.get(url)
                    .then(response => {
                    this.category = response.data.content;
                    this.total = response.data.totalElements;
                })
            },
            handleCurrentChange(val) {
                this.pageNum = val;
                this.list(val);
            },
            handleOpen(){
                this.form.id = '';
                this.form.name = '';
                this.fileList = [];
                this.dialogFormVisible = true;
            },
            handleEdit($index,row){
                this.form = row;
                this.fileList = [];
                this.dialogFormVisible = true;
            },
            handleFile(response) {
                this.fileList.push(response.file)
            },
            // eslint-disable-next-line no-unused-vars
            handleExceed(files) {
                this.$message.warning(`最多上传 ${files.length} 个文件`)
            },
            handleCancel(){
                this.dialogFormVisible = false;
                this.$ref['form'].resetFields();
            },
            handleAdd(){
                let formData = new FormData();
                formData.append('name', this.form.name);
                formData.append('file', this.fileList[0]);
                if(this.form.id === ''){
                    this.axios.post(this.uri, formData)
                        .then(response => {
                            if(0 != response.data.length) {
                                this.dialogFormVisible = false;
                                this.handleCurrentChange(1);
                            }
                        })
                }else{
                    let url= this.uri + "/" + this.form.id;
                    this.axios.put(url, formData)
                        .then(response => {
                            if(0 != response.data.length) {
                                this.dialogFormVisible = false;
                                this.handleCurrentChange(1);
                            }
                        })
                }
            },
            handleDelete($index,row){
                let url = this.uri + "/" + row.id;
                this.axios.delete(url)
                    .then(response => {
                    if( 0 === response.data.length ) {
                        this.category.splice($index, 1);
                        this.handleCurrentChange(1);
                    }
                });
            }
        }
    }
</script>

<style scoped>
</style>