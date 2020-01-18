<template>
    <div>
        <el-row style="margin-left:20%;margin-right: 20%;">
            <h3>确认订单信息</h3>
            <el-card style="width: 300px">
                <el-form ref="form" :model="form" size="mini">
                    <el-form-item label="地址">
                        <el-input v-model="form.address"></el-input>
                    </el-form-item>
                    <el-form-item label="收件人姓名">
                        <el-input v-model="form.recipient"></el-input>
                    </el-form-item>
                    <el-form-item label="联系方式">
                        <el-input v-model="form.tel"></el-input>
                    </el-form-item>
                </el-form>
            </el-card>
        </el-row>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px">
        <el-table :data="orderItems"
                  border>
            <el-table-column prop="product.name"
                             label="商品信息"/>
            <el-table-column prop="product.price"
                             label="单价"/>
            <el-table-column prop="number"
                             label="数量"/>
        </el-table>
        </el-row>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px;text-align: right">
            <p>应付金额：</p>
            <h2 style="color: #fb4e44">{{total}}</h2>
            <el-button type="danger" @click="handleSubmit">提交订单</el-button>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Confirm",
        data(){
            return{
                uri:'confirm',
                total:0,
                oid:'',
                orderItems:[],
                product:{},
                form:{
                    address:'',
                    recipient:'',
                    tel:'',
                },
                user:'',
            }
        },
        created() {
            let id = this.$route.query.oiid;
            let url = this.uri + "/?oiid=" + id;
            this.axios.get(url)
                .then(response => {
                        let result = response.data;
                        this.total = result.data.total;
                        this.orderItems = result.data.orderItems;
                    }
                )
        },
        methods:{
            handleSubmit(){
                let url = '/createOrder';
                this.axios.post(url,this.form)
                    .then(response => {
                        let result = response.data;
                        let oid = result.data.oid;
                        let total = result.data.total;
                        this.$router.push({
                            path: 'pay',
                            query: {
                                oid : oid,
                                total : total
                            }
                        })
                    })
            }
        }
    }
</script>

<style scoped>

</style>