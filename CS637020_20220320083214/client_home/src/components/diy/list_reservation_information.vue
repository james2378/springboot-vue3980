<template>
	<div class="diy_home diy_list diy_reservation_information" id="diy_reservation_information_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/reservation_information/details?reservation_information_id=' + o['reservation_information_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/reservation_information/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/reservation_information/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','user')">用户
					</th>
					<th class="diy_title" v-if="$check_field('get','full_name')">姓名
					</th>
					<th class="diy_title" v-if="$check_field('get','contact_information')">联系方式
					</th>
					<th class="diy_title" v-if="$check_field('get','name_of_study_room')">自习室名称
					</th>
					<th class="diy_title" v-if="$check_field('get','study_room_type')">自习室类型
					</th>
					<th class="diy_title" v-if="$check_field('get','region')">区域
					</th>
					<th class="diy_title" v-if="$check_field('get','opening_hours')">开放时间
					</th>
					<th class="diy_title" v-if="$check_field('get','administrator_telephone')">管理员电话
					</th>
					<th class="diy_title" v-if="$check_field('get','address')">地址
					</th>
					<th class="diy_title" v-if="$check_field('get','hourly_rate')">每小时价格
					</th>
					<th class="diy_title" v-if="$check_field('get','seat_type')">座位类型
					</th>
					<th class="diy_title" v-if="$check_field('get','appointment_period')">预约时段
					</th>
					<th class="diy_title" v-if="$check_field('get','number_of_appointments')">预约人数
					</th>
					<th class="diy_title" v-if="$check_field('get','service_duration')">使用时长
					</th>
					<th class="diy_title" v-if="$check_field('get','cost')">费用
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_uid" v-if="$check_field('get','user')">
						<span>
							{{ get_user_name(o['user']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','full_name')">
						<span>
							{{ o["full_name"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','contact_information')">
						<span>
							{{ o["contact_information"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','name_of_study_room')">
						<span>
							{{ o["name_of_study_room"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','study_room_type')">
						<span>
							{{ o["study_room_type"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','region')">
						<span>
							{{ o["region"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','opening_hours')">
						<span>
							{{ o["opening_hours"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','administrator_telephone')">
						<span>
							{{ o["administrator_telephone"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','address')">
						<span>
							{{ o["address"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','hourly_rate')">
						<span>
							{{ o["hourly_rate"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','seat_type')">
						<span>
							{{ o["seat_type"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','appointment_period')">
						<span>
							{{ o["appointment_period"] }}
						</span>
					</td>
					<td class="diy_field diy_number" v-if="$check_field('get','number_of_appointments')">
						<span>
							{{ o["number_of_appointments"] }}
						</span>
					</td>
					<td class="diy_field diy_number" v-if="$check_field('get','service_duration')">
						<span>
							{{ o["service_duration"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','cost')">
						<span>
							{{ o["cost"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						],
						itemList: [
								{
									title: "用户",
									name: "user",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "姓名",
									name: "full_name",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "联系方式",
									name: "contact_information",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "自习室名称",
									name: "name_of_study_room",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "自习室类型",
									name: "study_room_type",
									type: "下拉",
									is_img_list: "0"
								},
								{
									title: "区域",
									name: "region",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "开放时间",
									name: "opening_hours",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "管理员电话",
									name: "administrator_telephone",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "地址",
									name: "address",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "每小时价格",
									name: "hourly_rate",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "座位类型",
									name: "seat_type",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "预约时段",
									name: "appointment_period",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "预约人数",
									name: "number_of_appointments",
									type: "数字",
									is_img_list: "0"
								},
								{
									title: "使用时长",
									name: "service_duration",
									type: "数字",
									is_img_list: "0"
								},
								{
									title: "费用",
									name: "cost",
									type: "文本",
									is_img_list: "0"
								},
						],
						richList: [
						],
				// 用户列表
				list_user_user: [],
			};
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_user() {
				var json = await this.$get("~/api/user/get_list?user_group=普通用户");
				if(json.result && json.result.list){
					this.list_user_user = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_name(id){
				var obj = this.list_user_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
		},
		created() {
			this.get_list_user_user();
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

