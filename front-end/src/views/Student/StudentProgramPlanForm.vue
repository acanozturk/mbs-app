<template>
  <div class="app">
    <left-menu-student/>

    <div id="StudentProgramPlanForm">
      <div>
        <h1>Program Plan Form Management</h1>

      </div>
      <div>{{ this.$store.state.user.programPlanForm.fileName }}
        <button type="button" :disabled='isDownloadDisabled' v-on:click="download()" style="margin-top:10px">Download
        </button>
        <button type="button" :disabled='isRemoveDisabled' v-on:click="remove()" style="margin-left:10px">Remove
        </button>
      </div>

      <div>
        <label>
          <input type="file" name="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
        </label>
        <button type="button" v-on:click="submit()" style="margin-top:10px">Submit</button>
      </div>
      <div>
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
  name: 'StudentProgramPlanForm',
  data() {
    return {
      file: "",
      response: "",
      disabledRemove: false,
      disabledDownload: false,
    };
  },
  mounted() {
    console.log(this.$store.state.user);
    if (this.$store.state.user.programPlanForm == null) {
      this.$store.state.user.programPlanForm = {
        fileName: `No Program Plan Form found for ${this.$store.state.user.firstName}`
      }
      this.disabledRemove = true;
      this.disabledDownload = true;
    }
  },
  computed: {
    isDownloadDisabled: function () {
      return this.disabledDownload;
    },
    isRemoveDisabled: function () {
      return this.disabledRemove;
    },
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
    submit() {
      this.clearError();
      let formData = new FormData();
      formData.append('file', this.file);
      console.log(formData);
      axios({
        method: "POST", "url": `http://localhost:8080/mbs/programPlanForm/upload/${this.$store.state.user.id}`,
        "data": formData, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            this.$store.state.user.programPlanForm.fileName = this.file.name;
            this.disabledRemove = false;
            this.disabledDownload = false;
            console.log(result.data);
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    download() {
      this.clearError();
      axios({
        method: "GET",
        "url": `http://localhost:8080/mbs/programPlanForm/getByStudent/${this.$store.state.user.id}`
      })
          .then(result => {
            console.log(result.data);
            window.open(result.data.url);
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    remove() {
      this.clearError();
      axios({method: "DELETE", "url": `http://localhost:8080/mbs/programPlanForm/delete/${this.$store.state.user.id}`})
          .then(result => {
            this.disabledRemove = true;
            this.disabledDownload = true;
            this.$store.state.user.programPlanForm = {
              fileName: `No Program Plan Form File for the Student ${this.$store.state.user.firstName} `
            }
            console.log(result.data);
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
  }
}

</script>
<style scoped>
#StudentProgramPlanForm {
  width: 1000px;
  height: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>
