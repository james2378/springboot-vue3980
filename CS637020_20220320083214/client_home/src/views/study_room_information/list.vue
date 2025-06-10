<template>
	<div class="diy_list page_study_room_information" id="study_room_information_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">自习室信息列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="商家搜索" v-model="query['business']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="自习室名称搜索" v-model="query['name_of_study_room']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="自习室类型搜索" v-model="query['study_room_type']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="区域搜索" v-model="query['region']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
								<b-dropdown text="商家" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','business')">全部</b-dropdown-item>
									<b-dropdown-item v-for="o in list_business" :key="o['user_id']" @click="filter_set(o['user_id'],'business')">
										{{ o['nickname'] + '-' + o['username'] }}
									</b-dropdown-item>
								</b-dropdown>
								<b-dropdown :text="title" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','study_room_type')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o,i) in list_study_room_type" @click="filter_set(o,'study_room_type')" >
										{{ o }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_study_room_information :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_study_room_information from "@/components/diy/list_study_room_information.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_study_room_information
		},
		data() {
			return {
				url_get_list: "~/api/study_room_information/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"business": 0, // 商家
					"name_of_study_room": "", // 自习室名称
					"study_room_type": "", // 自习室类型
					"region": "", // 区域
					"examine_state": "已通过", // 审核状态
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "`create_time` desc",
					},
					{
						name: "创建时间从低到高",
						value: "`create_time` asc",
					},
					{
						name: "更新时间从高到低",
						value: "`update_time` desc",
					},
					{
						name: "更新时间从低到高",
						value: "`update_time` asc",
					},
					{
						name: "商家正序",
						value: "`business` asc",
					},
					{
						name: "商家倒序",
						value: "`business` desc",
					},
					{
						name: "自习室名称正序",
						value: "`name_of_study_room` asc",
					},
					{
						name: "自习室名称倒序",
						value: "`name_of_study_room` desc",
					},
					{
						name: "自习室类型正序",
						value: "`study_room_type` asc",
					},
					{
						name: "自习室类型倒序",
						value: "`study_room_type` desc",
					},
					{
						name: "区域正序",
						value: "`region` asc",
					},
					{
						name: "区域倒序",
						value: "`region` desc",
					},
				],
				list_business: [],
				// 自习室类型列表
				"list_study_room_type": ['付费自习室','免费自习室'],
			}
		},
		methods: {
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},

			/**
			 * 筛选
			 */
			/**
			 * 获取商家列表
			 */
			async get_list_business() {
				var json = await this.$get("~/api/user/get_list?user_group=自习室管理员");
				if(json.result && json.result.list){
					this.list_business = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			filter_study_room_type(o) {
				if (o == "全部") {
					this.query["study_room_type"] = "";
				} else {
					this.query["study_room_type"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.business = ""
				this.query.name_of_study_room = ""
				this.query.study_room_type = ""
				this.query.region = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},
		},
		computed: {
		},
		created() {
			this.get_list_business();
		}
	}
</script>

<style>
</style>
