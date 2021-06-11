<template>
  <div class="app">
    <left-menu-jury/>

    <div id="JuryThesisManagement">
      <div id="ThesisManagement">

        <div style="margin-left:5px ">
          <h1>Thesis Management</h1>
        </div>

        <div>
          <button type="button" v-on:click="selectStudent()" style="margin-top:10px">Select Student</button>
        </div>
        <label>
          <select name="student" v-model="student" size="Number_of_options">
            <option v-for="(student, keyStudent) in students" :value="student" :key="keyStudent">{{ student.firstName }}
              {{ student.lastName }}
            </option>
          </select>
        </label>
        <!--  <div style="margin-left:5px "> Please enter Deadline date as YYYY-MM-DD:
             <input type="text" name="deadline" v-model="deadline" placeholder="Deadline" />
         </div>
         <div style="margin-left:5px "> Please enter the Thesis Topic:
             <input type="text" name="thesisTopic" v-model="thesisTopic" id="thesisTopic" placeholder="Thesis Topic" />
         </div> -->
        <div style="margin-left:5px ">
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
import LeftMenuJury from './JuryLeftMenu.vue';
import axios from "axios";

export default {
  components: {LeftMenuJury},
  name: 'JuryThesisManagement',
  data() {
    return {
      students: [],
      /*
      file: "",
      response: "",
      disabledRemove: false,
      disabledDownload: false,
      disabledGetPlagiarismRatio: false,
      deadline: "",
      thesisTopic: "",
      plagiarismRatio: "" */
    };
  },
  mounted() {
    console.log("User ID: " + this.$store.state.user.id);
    axios({
      method: "GET",
      "url": `http://localhost:8080/mbs/juryMembers/getTssJury/${this.$store.state.user.id}`
    }).then(result => {
      console.log(result.data);
      if (result.data.tssJuryList != null) {
        result.data.tssJuryList.forEach(element => {
          if (element.student != null) {
            this.students.push(element.student);
          }
        });
      }
    }, error => {
      console.error(error);
    });
  },
  computed: {/*
            isDownloadDisabled: function(){
                return this.disabledDownload;
            },
            isRemoveDisabled: function(){
                return this.disabledRemove;
            },
            isGetPlagiarismRatioDisabled: function(){
                return this.disabledGetPlagiarismRatio;
            }, */
  },
  methods: {/*
            clearError: function() {
                this.response = "";
            },
            handleFileUpload(){
                this.clearError();
                this.file = this.$refs.file.files[0];
                console.log(this.file);
            },
            submit() {
                this.clearError();
                let formData = new FormData();
                formData.append('file', this.file);
                console.log(formData);
                axios({ method: "POST", "url": `http://localhost:8080/mbs/thesis/upload/${this.$store.state.user.id}`,
                 "data": formData, "headers": { "content-type": "application/json" }  })
                .then(result => {
                    this.$store.state.user.thesis.fileName = this.file.name;
                    this.disabledRemove = false;
                    this.disabledDownload = false;
                    this.disabledGetPlagiarismRatio = false;
                    console.log(result.data);
                }).catch(error => {
                this.response = `${error.response.status}, ${error.response.data.errorMessage}`
                });
            },
            download() {
                this.clearError();
                axios({ method: "GET", "url": `http://localhost:8080/mbs/thesis/getByJury/${this.$store.state.user.id}`})
                .then(result => {
                    console.log(result.data);
                    window.open(result.data.url);
                }).catch(error => {
                this.response = `${error.response.status}, ${error.response.data.errorMessage}`
                });
            },
            remove() {
                this.clearError();
                axios({ method: "DELETE", "url": `http://localhost:8080/mbs/thesis/delete/${this.$store.state.user.id}`})
                .then(result => {
                    this.disabledRemove = true;
                    this.disabledDownload = true;
                    this.disabledGetPlagiarismRatio = true;
                    this.plagiarismRatio = "";
                    this.$store.state.user.thesis = {
                        fileName : `No Thesis File for the Jury ${this.$store.state.user.firstName} `
                    }
                    console.log(result.data);
                }).catch(error => {
                this.response = `${error.response.status}, ${error.response.data.errorMessage}`
                });
            },
            getPlagiarismRatio() {
                this.clearError();
                axios({ method: "GET", "url": `http://localhost:8080/mbs/Jurys/${this.$store.state.user.id}`})
                .then(result => {
                    console.log(result.data);
                    this.plagiarismRatio = result.data.Jury.thesis.plagiarismRatio;
                }).catch(error => {
                this.response = `${error.response.status}, ${error.response.data.errorMessage}`
                });
            } */
  }
}
</script>
<style scoped>
#JuryThesisManagement {
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