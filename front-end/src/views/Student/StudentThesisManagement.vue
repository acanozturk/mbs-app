<template>
  <div class="app">
    <left-menu-student/>

    <div id="StudentThesisManagement">
      <div id="ThesisManagement">

        <div style="margin-left:5px ">
          <h1>Thesis Management</h1>
        </div>

        <div style="margin-left:5px ">{{ this.$store.state.user.thesis.fileName }}
          <button type="button" :disabled='isDownloadDisabled' v-on:click="download()" id="downloadDisabled">Download
          </button>
          <button type="button" :disabled='isRemoveDisabled' v-on:click="remove()" style="margin-left:39px">Remove
          </button>
          <label style="margin-left:16px ">
            Plagiarism Ratio: {{ this.plagiarismRatio }}
            <button type="button" :disabled='isGetPlagiarismRatioDisabled' v-on:click="getPlagiarismRatio()"
                    style="margin-left:39px">Get Plagiarism Ratio
            </button>
          </label>
        </div>

        <div style="margin-left:5px ">
          <label>
            <input type="file" name="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
          </label>
          <button type="button" v-on:click="submit()" style="margin-top:10px">Submit</button>
        </div>
      </div>
      <div class="response">{{ this.response }}</div>
    </div>
  </div>
</template>

<script>
import LeftMenuStudent from './StudentLeftMenu.vue';
import axios from "axios";

export default {
  components: {LeftMenuStudent},
  name: 'StudentThesisManagement',
  data() {
    return {
      file: "",
      response: "",
      disabledRemove: false,
      disabledDownload: false,
      disabledGetPlagiarismRatio: false,
      deadline: "",
      thesisTopic: "",
      plagiarismRatio: ""
    };
  },
  mounted() {

    console.log(this.$store.state.user);
    if (this.$store.state.user.thesis == null) {
      this.$store.state.user.thesis = {
        fileName: `No thesis found for ${this.$store.state.user.firstName} `
      }
      this.disabledRemove = true;
      this.disabledDownload = true;
      this.disabledGetPlagiarismRatio = true;
    }

  },
  computed: {
    isDownloadDisabled: function () {
      return this.disabledDownload;
    },
    isRemoveDisabled: function () {
      return this.disabledRemove;
    },
    isGetPlagiarismRatioDisabled: function () {
      return this.disabledGetPlagiarismRatio;
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
        method: "POST", "url": `http://localhost:8080/mbs/thesis/upload/${this.$store.state.user.id}`,
        "data": formData, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            this.$store.state.user.thesis.fileName = this.file.name;
            this.disabledRemove = false;
            this.disabledDownload = false;
            this.disabledGetPlagiarismRatio = false;
            console.log(result.data);
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    download() {
      this.clearError();
      axios({method: "GET", "url": `http://localhost:8080/mbs/thesis/getByStudent/${this.$store.state.user.id}`})
          .then(result => {
            console.log(result.data);
            window.open(result.data.url);
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    remove() {
      this.clearError();
      axios({method: "DELETE", "url": `http://localhost:8080/mbs/thesis/delete/${this.$store.state.user.id}`})
          .then(result => {
            this.disabledRemove = true;
            this.disabledDownload = true;
            this.disabledGetPlagiarismRatio = true;
            this.plagiarismRatio = "";
            this.$store.state.user.thesis = {
              fileName: `No Thesis File for the Student ${this.$store.state.user.firstName} `
            }
            console.log(result.data);
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    getPlagiarismRatio() {
      this.clearError();
      axios({method: "GET", "url": `http://localhost:8080/mbs/students/${this.$store.state.user.id}`})
          .then(result => {
            console.log(result.data);
            this.plagiarismRatio = result.data.student.thesis.plagiarismRatio;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    }
  }
}

</script>
<style scoped>
#StudentThesisManagement {
  width: 1000px;
  height: 500px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}

#downloadDisabled {
  margin-top: 15px;
  margin-left: 20px;
}
</style>
