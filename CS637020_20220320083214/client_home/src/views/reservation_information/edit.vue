<template>
	<div class="diy_edit page_reservation_information" id="reservation_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','user') || $check_field('add','user') || $check_field('get','user')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user" :disabled="disabledObj['user_isDisabled']" v-model="form['user']" v-if="(form['user'] && $check_field('set','user')) || (!form['user'] && $check_field('add','user'))" >
								<option v-for="o in list_user_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user')">{{ form['user'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','full_name') || $check_field('add','full_name') || $check_field('get','full_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_full_name" v-model="form['full_name']" placeholder="请输入姓名" v-if="(form['full_name'] && $check_field('set','full_name')) || (!form['full_name'] && $check_field('add','full_name'))"  :disabled="disabledObj['full_name_isDisabled']"/>
							<span v-else-if="$check_field('get','full_name')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_information') || $check_field('add','contact_information') || $check_field('get','contact_information')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>联系方式:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_contact_information" v-model="form['contact_information']" placeholder="请输入联系方式" v-if="(form['contact_information'] && $check_field('set','contact_information')) || (!form['contact_information'] && $check_field('add','contact_information'))"  :disabled="disabledObj['contact_information_isDisabled']"/>
							<span v-else-if="$check_field('get','contact_information')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','name_of_study_room') || $check_field('add','name_of_study_room') || $check_field('get','name_of_study_room')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>自习室名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_name_of_study_room" v-model="form['name_of_study_room']" placeholder="请输入自习室名称" v-if="(form['name_of_study_room'] && $check_field('set','name_of_study_room')) || (!form['name_of_study_room'] && $check_field('add','name_of_study_room'))"  :disabled="disabledObj['name_of_study_room_isDisabled']"/>
							<span v-else-if="$check_field('get','name_of_study_room')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','study_room_type') || $check_field('add','study_room_type') || $check_field('get','study_room_type')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>自习室类型:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_study_room_type" v-model="form['study_room_type']" v-if="(form['study_room_type'] && $check_field('set','study_room_type')) || (!form['study_room_type'] && $check_field('add','study_room_type'))" >
								<option v-for="o in list_study_room_type" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','study_room_type')">{{ form['study_room_type'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','region') || $check_field('add','region') || $check_field('get','region')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>区域:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_region" v-model="form['region']" placeholder="请输入区域" v-if="(form['region'] && $check_field('set','region')) || (!form['region'] && $check_field('add','region'))"  :disabled="disabledObj['region_isDisabled']"/>
							<span v-else-if="$check_field('get','region')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','opening_hours') || $check_field('add','opening_hours') || $check_field('get','opening_hours')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>开放时间:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_opening_hours" v-model="form['opening_hours']" placeholder="请输入开放时间" v-if="(form['opening_hours'] && $check_field('set','opening_hours')) || (!form['opening_hours'] && $check_field('add','opening_hours'))"  :disabled="disabledObj['opening_hours_isDisabled']"/>
							<span v-else-if="$check_field('get','opening_hours')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','administrator_telephone') || $check_field('add','administrator_telephone') || $check_field('get','administrator_telephone')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>管理员电话:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_administrator_telephone" v-model="form['administrator_telephone']" placeholder="请输入管理员电话" v-if="(form['administrator_telephone'] && $check_field('set','administrator_telephone')) || (!form['administrator_telephone'] && $check_field('add','administrator_telephone'))"  :disabled="disabledObj['administrator_telephone_isDisabled']"/>
							<span v-else-if="$check_field('get','administrator_telephone')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','address') || $check_field('add','address') || $check_field('get','address')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>地址:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_address" v-model="form['address']" placeholder="请输入地址" v-if="(form['address'] && $check_field('set','address')) || (!form['address'] && $check_field('add','address'))"  :disabled="disabledObj['address_isDisabled']"/>
							<span v-else-if="$check_field('get','address')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','hourly_rate') || $check_field('add','hourly_rate') || $check_field('get','hourly_rate')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>每小时价格:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_hourly_rate" v-model="form['hourly_rate']" placeholder="请输入每小时价格" v-if="(form['hourly_rate'] && $check_field('set','hourly_rate')) || (!form['hourly_rate'] && $check_field('add','hourly_rate'))"  :disabled="disabledObj['hourly_rate_isDisabled']"/>
							<span v-else-if="$check_field('get','hourly_rate')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','seat_type') || $check_field('add','seat_type') || $check_field('get','seat_type')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>座位类型:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_seat_type" v-model="form['seat_type']" placeholder="请输入座位类型" v-if="(form['seat_type'] && $check_field('set','seat_type')) || (!form['seat_type'] && $check_field('add','seat_type'))"  :disabled="disabledObj['seat_type_isDisabled']"/>
							<span v-else-if="$check_field('get','seat_type')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','appointment_period') || $check_field('add','appointment_period') || $check_field('get','appointment_period')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>预约时段:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_appointment_period" v-model="form['appointment_period']" placeholder="请输入预约时段" v-if="(form['appointment_period'] && $check_field('set','appointment_period')) || (!form['appointment_period'] && $check_field('add','appointment_period'))"  :disabled="disabledObj['appointment_period_isDisabled']"/>
							<span v-else-if="$check_field('get','appointment_period')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','number_of_appointments') || $check_field('add','number_of_appointments') || $check_field('get','number_of_appointments')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>预约人数:
							</span>
						</div>
						<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_number_of_appointments" v-model.number="form['number_of_appointments']" placeholder="请输入预约人数" v-if="(form['number_of_appointments'] && $check_field('set','number_of_appointments')) || (!form['number_of_appointments'] && $check_field('add','number_of_appointments'))" :disabled="disabledObj['number_of_appointments_isDisabled']" />
							<span v-else-if="$check_field('get','number_of_appointments')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','service_duration') || $check_field('add','service_duration') || $check_field('get','service_duration')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>使用时长:
							</span>
						</div>
						<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_service_duration" v-model.number="form['service_duration']" placeholder="请输入使用时长" v-if="(form['service_duration'] && $check_field('set','service_duration')) || (!form['service_duration'] && $check_field('add','service_duration'))" :disabled="disabledObj['service_duration_isDisabled']" />
							<span v-else-if="$check_field('get','service_duration')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','cost') || $check_field('add','cost') || $check_field('get','cost')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>费用:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_cost" v-model="form['cost']" placeholder="请输入费用" v-if="(form['cost'] && $check_field('set','cost')) || (!form['cost'] && $check_field('add','cost'))"  @focus="set_cost()" :disabled="disabledObj['cost_isDisabled']"/>
							<span v-else-if="$check_field('get','cost')">{{ form['full_name'] }}</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/reservation_information/get_obj?",
				url_add: "~/api/reservation_information/add?",
				url_set: "~/api/reservation_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"user": 0,
					"full_name": "",
					"contact_information": "",
					"name_of_study_room": "",
					"study_room_type": "",
					"region": "",
					"opening_hours": "",
					"administrator_telephone": "",
					"address": "",
					"hourly_rate": "",
					"seat_type": "",
					"appointment_period": "",
					"number_of_appointments": 0,
					"service_duration": 0,
					"cost": "",
					"reservation_information_id": 0,
				},

				obj: {
					"user": 0, // 用户
					"full_name":'', // 姓名
					"contact_information":'', // 联系方式
					"name_of_study_room":'', // 自习室名称
					"study_room_type":'', // 自习室类型
					"region":'', // 区域
					"opening_hours":'', // 开放时间
					"administrator_telephone":'', // 管理员电话
					"address":'', // 地址
					"hourly_rate":'', // 每小时价格
					"seat_type":'', // 座位类型
					"appointment_period":'', // 预约时段
					"number_of_appointments":0, // 预约人数
					"service_duration":0, // 使用时长
					"cost":'', // 费用
					"reservation_information_id": 0,
				},

				// 表单字段
				form: {
					"user": 0, // 用户
					"full_name":'', // 姓名
					"contact_information":'', // 联系方式
					"name_of_study_room":'', // 自习室名称
					"study_room_type":'', // 自习室类型
					"region":'', // 区域
					"opening_hours":'', // 开放时间
					"administrator_telephone":'', // 管理员电话
					"address":'', // 地址
					"hourly_rate":'', // 每小时价格
					"seat_type":'', // 座位类型
					"appointment_period":'', // 预约时段
					"number_of_appointments":0, // 预约人数
					"service_duration":0, // 使用时长
					"cost":'', // 费用
					"reservation_information_id": 0,

				},
				disabledObj:{
					"user_isDisabled": false,
					"full_name_isDisabled": false,
					"contact_information_isDisabled": false,
					"name_of_study_room_isDisabled": false,
					"study_room_type_isDisabled": false,
					"region_isDisabled": false,
					"opening_hours_isDisabled": false,
					"administrator_telephone_isDisabled": false,
					"address_isDisabled": false,
					"hourly_rate_isDisabled": false,
					"seat_type_isDisabled": false,
					"appointment_period_isDisabled": false,
					"cost_isDisabled": false,
				},
				// 用户列表
				list_user_user: [],
				//自习室类型选项列表
				list_study_room_type: ['付费自习室','免费自习室'],

				// ID字段
				field: "reservation_information_id",
			}
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_user() {
				// if(this.user_group !== "管理员" && this.form["user"] === 0) {
				//     this.form["user"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=普通用户");
				if(json.result && json.result.list){
					this.list_user_user = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_user(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user"] = user_id
								_this.disabledObj['user' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="user") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			set_cost(){
				this.form.cost =this.form.service_duration * this.form.number_of_appointments * this.form.hourly_rate
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/reservation_information/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_user();
			this.get_list_user_user();
		}
	}
</script>

<style>
</style>
