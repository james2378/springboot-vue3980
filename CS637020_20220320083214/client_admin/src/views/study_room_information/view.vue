<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','business') || $check_field('add','business') || $check_field('set','business')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商家" prop="business">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_business(form['business']) }}
							<!--<el-input id="business_name" v-model="form['business']" placeholder="请输入商家"-->
							<!--v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','business')) || (!form['study_room_information_id'] && $check_field('add','business'))" :disabled="disabledObj['business_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','business')">{{form['business']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','business')) || (!form['study_room_information_id'] && $check_field('add','business'))" id="business" v-model="form['business']" :disabled="disabledObj['business_isDisabled']">
								<el-option v-for="o in list_user_business" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','business')" id="business" v-model="form['business']" :disabled="true">
								<el-option v-for="o in list_user_business" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="business" v-model="form['business']" :disabled="disabledObj['business_isDisabled']">
							<el-option v-for="o in list_user_business" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_study_room') || $check_field('add','name_of_study_room') || $check_field('set','name_of_study_room')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="自习室名称" prop="name_of_study_room">
					<el-input id="name_of_study_room" v-model="form['name_of_study_room']" placeholder="请输入自习室名称"
							  v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','name_of_study_room')) || (!form['study_room_information_id'] && $check_field('add','name_of_study_room'))" :disabled="disabledObj['name_of_study_room_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_study_room')">{{form['name_of_study_room']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','study_room_type') || $check_field('add','study_room_type') || $check_field('set','study_room_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="自习室类型" prop="study_room_type">
					<el-select id="study_room_type" v-model="form['study_room_type']"
						v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','study_room_type')) || (!form['study_room_information_id'] && $check_field('add','study_room_type'))">
						<el-option v-for="o in list_study_room_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','study_room_type')">{{form['study_room_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','region') || $check_field('add','region') || $check_field('set','region')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="区域" prop="region">
					<el-input id="region" v-model="form['region']" placeholder="请输入区域"
							  v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','region')) || (!form['study_room_information_id'] && $check_field('add','region'))" :disabled="disabledObj['region_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','region')">{{form['region']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','opening_hours') || $check_field('add','opening_hours') || $check_field('set','opening_hours')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="开放时间" prop="opening_hours">
					<el-input id="opening_hours" v-model="form['opening_hours']" placeholder="请输入开放时间"
							  v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','opening_hours')) || (!form['study_room_information_id'] && $check_field('add','opening_hours'))" :disabled="disabledObj['opening_hours_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','opening_hours')">{{form['opening_hours']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','administrator_telephone') || $check_field('add','administrator_telephone') || $check_field('set','administrator_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="管理员电话" prop="administrator_telephone">
					<el-input id="administrator_telephone" v-model="form['administrator_telephone']" placeholder="请输入管理员电话"
							  v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','administrator_telephone')) || (!form['study_room_information_id'] && $check_field('add','administrator_telephone'))" :disabled="disabledObj['administrator_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','administrator_telephone')">{{form['administrator_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','address') || $check_field('add','address') || $check_field('set','address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="地址" prop="address">
					<el-input id="address" v-model="form['address']" placeholder="请输入地址"
							  v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','address')) || (!form['study_room_information_id'] && $check_field('add','address'))" :disabled="disabledObj['address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','address')">{{form['address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_seats') || $check_field('add','number_of_seats') || $check_field('set','number_of_seats')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="座位数量" prop="number_of_seats">
					<el-input id="number_of_seats" v-model="form['number_of_seats']" placeholder="请输入座位数量"
							  v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','number_of_seats')) || (!form['study_room_information_id'] && $check_field('add','number_of_seats'))" :disabled="disabledObj['number_of_seats_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_seats')">{{form['number_of_seats']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','seat_type') || $check_field('add','seat_type') || $check_field('set','seat_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="座位类型" prop="seat_type">
					<el-input id="seat_type" v-model="form['seat_type']" placeholder="请输入座位类型"
							  v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','seat_type')) || (!form['study_room_information_id'] && $check_field('add','seat_type'))" :disabled="disabledObj['seat_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','seat_type')">{{form['seat_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','spare_seats') || $check_field('add','spare_seats') || $check_field('set','spare_seats')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="空余座位" prop="spare_seats">
					<el-input-number id="spare_seats" v-model.number="form['spare_seats']"
						v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','spare_seats')) || (!form['study_room_information_id'] && $check_field('add','spare_seats'))"></el-input-number>
					<div v-else-if="$check_field('get','spare_seats')">{{form['spare_seats']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hourly_rate') || $check_field('add','hourly_rate') || $check_field('set','hourly_rate')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="每小时价格" prop="hourly_rate">
					<el-input id="hourly_rate" v-model="form['hourly_rate']" placeholder="请输入每小时价格"
							  v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','hourly_rate')) || (!form['study_room_information_id'] && $check_field('add','hourly_rate'))" :disabled="disabledObj['hourly_rate_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','hourly_rate')">{{form['hourly_rate']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','self_study_room_pictures') || $check_field('add','self_study_room_pictures') || $check_field('set','self_study_room_pictures')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="自习室图片" prop="self_study_room_pictures">
					<el-upload :disabled="disabledObj['self_study_room_pictures_isDisabled']" id="self_study_room_pictures" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_self_study_room_pictures"
						:show-file-list="false" v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','self_study_room_pictures')) || (!form['study_room_information_id'] && $check_field('add','self_study_room_pictures'))">
						<img v-if="form['self_study_room_pictures']" :src="$fullUrl(form['self_study_room_pictures'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','self_study_room_pictures')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['self_study_room_pictures'])" :preview-src-list="[$fullUrl(form['self_study_room_pictures'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','introduction_to_self_study_room') || $check_field('add','introduction_to_self_study_room') || $check_field('set','introduction_to_self_study_room')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="自习室简介" prop="introduction_to_self_study_room">
					<el-input type="textarea" id="introduction_to_self_study_room" v-model="form['introduction_to_self_study_room']" placeholder="请输入自习室简介"
						v-if="user_group === '管理员' || (form['study_room_information_id'] && $check_field('set','introduction_to_self_study_room')) || (!form['study_room_information_id'] && $check_field('add','introduction_to_self_study_room'))" :disabled="disabledObj['introduction_to_self_study_room_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','introduction_to_self_study_room')">{{form['introduction_to_self_study_room']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else-if="$check_field('get','examine_reply')">{{form["examine_reply"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "study_room_information_id",
				url_add: "~/api/study_room_information/add?",
				url_set: "~/api/study_room_information/set?",
				url_get_obj: "~/api/study_room_information/get_obj?",
				url_upload: "~/api/study_room_information/upload?",

				query: {
					"study_room_information_id": 0,
				},

				form: {
					"business": 0, // 商家
					"name_of_study_room":'', // 自习室名称
					"study_room_type":'', // 自习室类型
					"region":'', // 区域
					"opening_hours":'', // 开放时间
					"administrator_telephone":'', // 管理员电话
					"address":'', // 地址
					"number_of_seats":'', // 座位数量
					"seat_type":'', // 座位类型
					"spare_seats":0, // 空余座位
					"hourly_rate":'', // 每小时价格
					"self_study_room_pictures":'', // 自习室图片
					"introduction_to_self_study_room":'', // 自习室简介
					"examine_state": "未审核",
					"examine_reply": "",
					"study_room_information_id": 0, // ID

				},
				disabledObj:{
					"business_isDisabled": false,
					"name_of_study_room_isDisabled": false,
					"study_room_type_isDisabled": false,
					"region_isDisabled": false,
					"opening_hours_isDisabled": false,
					"administrator_telephone_isDisabled": false,
					"address_isDisabled": false,
					"number_of_seats_isDisabled": false,
					"seat_type_isDisabled": false,
					"hourly_rate_isDisabled": false,
					"self_study_room_pictures_isDisabled": false,
					"introduction_to_self_study_room_isDisabled": false,
				},
				// 用户列表
				list_user_business: [],
				// 用户组
				group_user_business: "",
				//自习室类型选项列表
				list_study_room_type: ['付费自习室','免费自习室'],

			}
		},
		methods: {
			/**
			 * 获取自习室管理员用户列表
			 */
			async get_list_user_business() {
                // if(this.user_group !== "管理员" && this.form["business"] === 0) {
                //     this.form["business"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=自习室管理员");
                if(json.result && json.result.list){
                    this.list_user_business = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取自习室管理员用户组
			 */
			async get_group_user_business() {
				this.form["business"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=自习室管理员");
				if(json.result && json.result.obj){
					this.group_user_business = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_business(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_business.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="business") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_business(id){
				var obj = this.list_user_business.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 上传自习室图片
			 * @param {Object} param图片参数
			 */
			upload_self_study_room_pictures(param){
				this.uploadFile(param.file, "self_study_room_pictures");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "商家":
							if(param["business"] > 0){
								param["business"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/study_room_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/study_room_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/study_room_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/study_room_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/study_room_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_business();
			this.get_group_user_business();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
