<template>
  <div class="app">
    <left-menu-advisor/>

    <div id="AdvisorSubmitThesisCopies">
      <div>
        <h1>Submit Thesis</h1>
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
      <div style="margin-left:500px ">{{ this.fileName }}
        <button type="button" :disabled='isButtonDisabled' v-on:click="download()" id="downloadDisabled">Download
          Thesis
        </button>
      </div>
      <div>
        <p>
          Selected Student ID : {{ this.studentId }}
        <p>
          Thesis Topic : {{ this.thesisTopic }}
        <p>
          Program Plan Form : {{ this.programPlanFormStatus }}
        </p>
      </div>
      <label style="margin-left:16px ">
        Plagiarism Ratio: {{ this.plagiarismRatio }}
        <div>
          <button type="button" v-on:click="getPlagiarismRatio()" style="margin-top:10px">Get Plagiarism Ratio</button>
        </div>
      </label>
      <div>
        <button type="button" v-on:click="submitThesis()" style="margin-top:10px">Submit Thesis for T.S.S</button>
      </div>
      <div>
        {{ this.response }}
      </div>
    </div>
  </div>
</template>

<script>
import LeftMenuAdvisor from './AdvisorLeftMenu.vue';
import axios from "axios";

export default {
  components: {LeftMenuAdvisor},
  name: 'AdvisorSubmitThesisCopies',
  data() {
    return {
      disabledButton: true,
      programPlanFormStatus: "",
      studentId: "",
      students: [],
      studentList: [],
      proposals: [],
      student: "",
      thesisTopic: "",
      plagiarismRatio: "",
      fileName: "",
      selectedAdvisor: null,
      input: {
        studentId: "",
      },
      response: "",
    };
  },
  computed: {
    isButtonDisabled: function () {
      return this.disabledButton;
    },
  },
  methods: {
    clearError: function () {
      this.response = "";
    },
    submitThesis() {
      this.clearError();
      this.input.studentId = this.studentId;
      console.log(this.input);
      axios({
        method: "POST", "url": "http://localhost:8080/mbs/thesis/submitThesis",
        "data": this.input, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            console.log(result.data);
            this.response = result.data.successMessage;
          }).catch(error => {
        this.response = `${error.response.status}, ${error.response.data.errorMessage}`
      });
    },
    selectStudent() {
      this.clearError();
      this.plagiarismRatio = "";
      this.studentId = this.student.id;
      if (this.student.thesis != null && this.student.thesis.fileName != null) {
        this.fileName = this.student.thesis.fileName;
        this.disabledButton = false;
      } else {
        this.fileName = "";
      }
      if (this.student.programPlanForm != null) {
        this.programPlanFormStatus = this.student.programPlanForm.programPlanFormStatus;
      } else {
        this.programPlanFormStatus = "";
      }
      if (this.student.thesisTopic != null) {
        this.thesisTopic = this.student.thesisTopic;
      } else {
        this.thesisTopic = "";
      }
    },
    getPlagiarismRatio() {
      this.clearError();
      if (this.student.thesis != null) {
        this.plagiarismRatio = this.student.thesis.plagiarismRatio;
      } else {
        this.response = "Thesis is not Uploaded."
      }
    },
    download() {
      this.clearError();
      axios({method: "GET", "url": `http://localhost:8080/mbs/thesis/getByStudent/${this.studentId}`})
          .then(result => {
            console.log(result.data);
            window.open(result.data.url);
          }).catch(error => {
        this.response = `${error.response.status}, ${error.response.data.errorMessage}`
      });
    },
  },
  mounted() {
    console.log("User ID: " + this.$store.state.user.id);
    axios({
      method: "GET",
      "url": `http://localhost:8080/mbs/advisors/getApprovedStudents/${this.$store.state.user.id}`
    }).then(result => {
      console.log(result.data.studentList);
      if (result.data.studentList != null) {
        result.data.studentList.forEach(element => {
          if (element != null) {
            this.students.push(element);
          }
        });
      }
    }, error => {
      this.response = `${error.response.status}, ${error.response.data.errorMessage}`
      console.error(error.response);
    });

  },
}
</script>

<style scoped>
#AdvisorSubmitThesisCopies {
  width: 1000px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>