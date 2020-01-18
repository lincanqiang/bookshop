<template>
    <div>
        <el-table :data="bean">
            <el-table-column type="expand">
                <template slot-scope="scope">
                    <el-form label-position="left">
                        <el-form-item label="创建时间">
                            <span>{{ scope.row.createTime}}</span>
                        </el-form-item>
                        <el-form-item label="支付时间">
                            <span>{{ scope.row.payTime}}</span>
                        </el-form-item>
                        <el-form-item label="发货时间">
                            <span>{{ scope.row.deliverTime}}</span>
                        </el-form-item>
                        <el-form-item label="成交时间">
                            <span>{{scope.row.dealTime}}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column
                    prop="id"
                    label="订单ID"/>
            <el-table-column
                    prop="statusDesc"
                    label="状态"/>
            <el-table-column
                    prop="amount"
                    label="金额"/>
            <el-table-column
                    prop="sum"
                    label="商品数量"/>
            <el-table-column
                    prop="recipient"
                    label="收件人"/>
            <el-table-column
                    label="操作">
                <template slot-scope="scope">
                    <el-button
                            type="danger"
                            icon="el-icon-position"
                            v-show="scope.row.status === 'waitDelivery'"
                            @click=handleDeliver(scope.$index,scope.row)>发货
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
                    :hide-on-single-page="value"
                    @current-change="handleCurrentChange"
                    :total="total"
                    :current-page="pageNum"
            />
        </el-col>
    </div>
</template>

<script>
    export default {
        name: "Order",
        data() {
            return {
                uri: 'orders',
                total: 0,
                pageNum: 0,
                bean: [],
            }
        },
        mounted() {
            this.list(1);
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
            handleDeliver($index,row){
                let url = this.uri + "/" + row.id;
                this.axios.put(url)
                    .then(response => {
                        if(0 != response.data.length) {
                            this.handleCurrentChange(1);
                        }
                    })
            },
        }
    }
</script>

<style scoped>

</style>