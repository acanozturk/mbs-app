<template>
  <div class="app">
    <LeftMenuStudent/>
    <div id="StudentFormMenu">
      <div>
        <h1>Form Menu</h1>
        <div>

          <label>
            <input type="file" name="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
          </label>
          <button type="button" v-on:click="uploadDM()" style="margin-left:10px">Upload Form-DM</button>
        </div>
        <div>

          <label>
            <input type="file" name="file2" id="file2" ref="file2" v-on:change="handleFileUpload2()"/>
          </label>
          <button type="button" v-on:click="uploadES()" style="margin-left:10px">Upload Form-ES</button>
        </div>
        <div>

          <label>
            <input type="file" name="file3" id="file3" ref="file3" v-on:change="handleFileUpload3()"/>
          </label>
          <button type="button" v-on:click="uploadGD()" style="margin-left:10px">Upload Form-GD</button>
        </div>
        <div>

          <label>
            <input type="file" name="file4" id="file4" ref="file4" v-on:change="handleFileUpload4()"/>
          </label>
          <button type="button" v-on:click="uploadTD()" style="margin-left:10px">Upload Form-TD</button>
        </div>
        <div>

          <label>
            <input type="file" name="file5" id="file5" ref="file5" v-on:change="handleFileUpload5()"/>
          </label>
          <button type="button" v-on:click="uploadTJ()" style="margin-left:10px">Upload Form-TJ</button>
        </div>
        <div style="margin-bottom:10px">

          <label>
            <input type="file" name="file6" id="file6" ref="file6" v-on:change="handleFileUpload6()"/>
          </label>
          <button type="button" v-on:click="uploadTJA()" style="margin-left:10px">Upload Form-TJ-A</button>
        </div><!---->
        {{ this.response }}
      </div>
    </div>
  </div>
</template>

<script>
import LeftMenuStudent from './StudentLeftMenu.vue';
import axios from "axios";

export default {
  components: {LeftMenuStudent},
  name: 'StudentFormMenu',
  data() {
    return {
      file: "",
      file2: "",
      file3: "",
      file4: "",
      file5: "",
      file6: "",
      response: "",
      input: "",
    };
  },
  mounted() {/*
        console.log(this.$store.state.user.id);
            this.input.studentId = this.$store.state.user.id;
            this.input.type = "dm";
            axios({ method: "POST",
                 "url": "http://localhost:8080/mbs/forms/getForm",
                 "data": this.input,
                 "headers": { "content-type": "application/json" } })
            .then(result => {
                    console.log(result.data);
                    if (result.data.success != true) {
                      this.disabledDMDownload = true;
                    }
                }, error => {
                    console.error(error.response);
              });
            this.input.type = "es";
            axios({ method: "POST",
                 "url": "http://localhost:8080/mbs/forms/getForm",
                 "data": this.input,
                 "headers": { "content-type": "application/json" } }).then(result => {
                    console.log(result.data);
                    if (result.data.success != true) {
                      this.disabledESDownload = true;
                    }
                }, error => {
                    console.error(error.response);
              });
              this.input.type = "gd";
              axios({ method: "POST",
                 "url": "http://localhost:8080/mbs/forms/getForm",
                 "data": this.input,
                 "headers": { "content-type": "application/json" } }).then(result => {
                    console.log(result.data);
                    if (result.data.success != true) {
                      this.disabledGDDownload = true;
                    }
                }, error => {
                    console.error(error.response);
              });
              this.input.type = "td";
              axios({ method: "POST",
                 "url": "http://localhost:8080/mbs/forms/getForm",
                 "data": this.input,
                 "headers": { "content-type": "application/json" } }).then(result => {
                    console.log(result.data);
                    if (result.data.success != true) {
                      this.disabledTDDownload = true;
                    }
                }, error => {
                    console.error(error.response);
              });
              this.input.type = "tj";
              axios({ method: "POST",
                 "url": "http://localhost:8080/mbs/forms/getForm",
                 "data": this.input,
                 "headers": { "content-type": "application/json" } }).then(result => {
                    console.log(result.data);
                    if (result.data.success != true) {
                      this.disabledTJDownload = true;
                    }
                }, error => {
                    console.error(error.response);
              });
              this.input.type = "tja";
              axios({ method: "POST",
                 "url": "http://localhost:8080/mbs/forms/getForm",
                 "data": this.input,
                 "headers": { "content-type": "application/json" } }).then(result => {
                    console.log(result.data);
                    if (result.data.success != true) {
                      this.disabledTJADownload = true;
                    }
                }, error => {
                    console.error(error.response);
              }); */
  },
  computed: {/*
            isDMDownloadDisabled: function(){
                return this.disabledDMDownload;
            },
            isESDownloadDisabled: function(){
                return this.disabledESDownload;
            },
            isGDDownloadDisabled: function(){
                return this.disabledGDDownload;
            },
            isTDDownloadDisabled: function(){
                return this.disabledTDDownload;
            },
            isTJDownloadDisabled: function(){
                return this.disabledTJDownload;
            },
            isTJADownloadDisabled: function(){
                return this.disabledTJADownload;
            }, */
  },
  methods: {
    clearError: function () {
      this.response = "";
    },
    handleFileUpload() {
      this.clearError();
      this.file = this.$refs.file.files[0];
      console.log(this.file);
    },
    handleFileUpload2() {
      this.clearError();
      this.file2 = this.$refs.file2.files[0];
      console.log(this.file2);
    },
    handleFileUpload3() {
      this.clearError();
      this.file3 = this.$refs.file3.files[0];
      console.log(this.file3);
    },
    handleFileUpload4() {
      this.clearError();
      this.file4 = this.$refs.file4.files[0];
      console.log(this.file4);
    },
    handleFileUpload5() {
      this.clearError();
      this.file5 = this.$refs.file5.files[0];
      console.log(this.file5);
    },
    handleFileUpload6() {
      this.clearError();
      this.file6 = this.$refs.file6.files[0];
      console.log(this.file6);
    },
    uploadDM() {
      this.clearError();
      let formData = new FormData();
      formData.append('file', this.file);
      console.log(formData);
      console.log(this.$store.state.user.id);
      axios({
        method: "POST", "url": `http://localhost:8080/mbs/forms/uploadForm/dm/${this.$store.state.user.id}`,
        "data": formData, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            //this.disabledDMDownload = false;
            this.response = result.data.successMessage;
            console.log(result.data);
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    uploadES() {
      this.clearError();
      let formData = new FormData();
      console.log(this.file2);
      formData.append('file', this.file2);
      console.log(formData);
      axios({
        method: "POST", "url": `http://localhost:8080/mbs/forms/uploadForm/es/${this.$store.state.user.id}`,
        "data": formData, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            //this.disabledESDownload = false;
            console.log(result.data);
            this.response = result.data.successMessage;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    uploadGD() {
      this.clearError();
      let formData = new FormData();
      formData.append('file', this.file3);
      console.log(formData);
      axios({
        method: "POST", "url": `http://localhost:8080/mbs/forms/uploadForm/gd/${this.$store.state.user.id}`,
        "data": formData, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            //this.disabledGDDownload = false;
            console.log(result.data);
            this.response = result.data.successMessage;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    uploadTD() {
      this.clearError();
      let formData = new FormData();
      formData.append('file', this.file4);
      console.log(formData);
      axios({
        method: "POST", "url": `http://localhost:8080/mbs/forms/uploadForm/td/${this.$store.state.user.id}`,
        "data": formData, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            //this.disabledTDDownload = false;
            console.log(result.data);
            this.response = result.data.successMessage;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    uploadTJ() {
      this.clearError();
      let formData = new FormData();
      formData.append('file', this.file5);
      console.log(formData);
      axios({
        method: "POST", "url": `http://localhost:8080/mbs/forms/uploadForm/tj/${this.$store.state.user.id}`,
        "data": formData, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            //this.disabledTJDownload = false;
            console.log(result.data);
            this.response = result.data.successMessage;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    uploadTJA() {
      this.clearError();
      let formData = new FormData();
      formData.append('file', this.file6);
      axios({
        method: "POST", "url": `http://localhost:8080/mbs/forms/uploadForm/tja/${this.$store.state.user.id}`,
        "data": formData, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            //this.disabledTJADownload = false;
            console.log(result.data);
            this.response = result.data.successMessage;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
  }
}
</script>

<style scoped>
#StudentFormMenu {
  width: 1000px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>