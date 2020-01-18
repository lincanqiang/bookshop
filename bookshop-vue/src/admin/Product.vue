<template>
    <div>
        <div>
            <el-dialog title="编辑商品"
                       :visible.sync="dialogFormVisible"
                       center
                       width="20%" >
                <el-form ref="form"
                        :model="form"
                        size="mini">
                    <el-form-item>
                        <el-tag v-model="form.id"
                                v-show="false"/>
                    </el-form-item>
                    <el-form-item label="商品图片">
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
                    <el-form-item label="商品名称">
                        <el-input v-model="form.name"/>
                    </el-form-item>
                    <el-form-item label="作者">
                        <el-input v-model="form.author"/>
                    </el-form-item>
                    <el-form-item label="价格">
                        <el-input v-model="form.price"/>
                    </el-form-item>
                    <el-form-item label="折扣">
                        <el-input v-model="form.discount"/>
                    </el-form-item>
                    <el-form-item label="出版社">
                        <el-input v-model="form.publisher"/>
                    </el-form-item>
                    <el-form-item label="库存">
                        <el-input v-model="form.stock"/>
                    </el-form-item>
                    <el-form-item label="分类ID">
                        <el-input v-model="form.cid"/>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="handleAdd">确 定</el-button>
                    <el-button @click="handleCancel">取 消</el-button>
                </div>
            </el-dialog>
            <el-select v-model="value"
                       filterable
                       @change="handleSelect"
                       clearable
                       @clear="handleBack"
                       placeholder="选择分类"
                       style="margin-bottom: 30px">
                <el-option
                        v-for="item in options"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                </el-option>
            </el-select>
            <el-button
                    type="primary"
                    icon="el-icon-circle-plus-outline"
                    @click="handleOpen">添加商品
            </el-button>
        </div>
        <div>
            <el-table :data="bean">
                <el-table-column
                        prop="id"
                        label="商品ID"
                        />
                <el-table-column
                        prop="name"
                        label="商品名称"
                        />
                <el-table-column
                        prop="image"
                        label="分类图片"
                        >
                    <template slot-scope="scope">
                        <el-image
                                style="width: 100px; height: 100px"
                                :src="scope.row.image"
                                fit="fill"/>
                    </template>
                </el-table-column>
                <el-table-column
                    prop="author"
                    label="作者"/>
                <el-table-column
                        prop="price"
                        label="价格"/>
                <el-table-column
                        prop="discount"
                        label="折扣信息"/>
                <el-table-column
                    prop="publisher"
                    label="出版社"/>
                <el-table-column
                    prop="stock"
                    label="库存"/>
                <el-table-column
                        label="操作">
                    <template slot-scope="scope">
                        <el-button
                                type="primary"
                                icon="el-icon-edit"
                                @click="handleEdit(scope.$index,scope.row)">
                        </el-button>
                        <el-button
                                type="danger"
                                icon="el-icon-delete"
                                @click="handleDelete(scope.$index,scope.row)">
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
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
        name: "Product",

        data(){
            return{
                uri:'products',
                total:0,
                pageNum:1,
                bean:[],
                options:[],
                value:'',
                dialogFormVisible:false,
                form:{
                    id:'',
                    name:'',
                    price:'',
                    author:'',
                    publisher:'',
                    discount:'',
                    stock:'',
                    cid:'',
                },
                fileList:[]
            }
        },

        mounted() {
            this.list(1);
            this.axios.get('category')
                .then(response => {
                    this.options = response.data;
            })
        },

        methods: {
            list(pageNum) {
                let url = this.uri + "?pageNum=" + pageNum;
                this.axios.get(url)
                    .then(response => {
                        this.bean = response.data.content;
                        this.total = response.data.totalElements;
                    })
            },
            handleCurrentChange(val) {
                this.pageNum = val;
                this.list(val);
            },
            handleSelect(id){
                let url = "categories/" + id + "/products" + "?pageNum=" + this.pageNum;
                this.axios.get(url)
                    .then(
                        response => {
                            this.bean = response.data.content;
                            this.total = response.data.totalElements;
                        }
                    )
            },
            handleBack(){
              this.list(1);
            },
            handleOpen(){
                this.form.id = '';
                this.form.name = '';
                this.form.price = '';
                this.form.author = '';
                this.form.publisher = '';
                this.form.discount = '';
                this.form.stock = '';
                this.form.cid = '';
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
                this.list(1);
            },
            handleAdd(){
                let formData = new FormData();
                formData.append('name', this.form.name);
                formData.append('price', this.form.price);
                formData.append('author', this.form.author);
                formData.append('publisher', this.form.publisher);
                formData.append('discount', this.form.discount);
                formData.append('stock', this.form.stock);
                formData.append('cid',this.form.cid);
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
                            this.bean.splice($index, 1);
                            this.handleCurrentChange(1);
                        }
                    });
            }
        }
    }
</script>

<style scoped>

</style>