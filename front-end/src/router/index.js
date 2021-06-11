import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginComponent from "../views/login.vue"
import StudentHomeComponent from "../views/Student/StudentHome.vue"
import StudenAdvisorSelection from "../views/Student/StudentAdvisorSelection.vue"
import StudentProgramPlanForm from "../views/Student/StudentProgramPlanForm.vue"
import StudentThesisManagement from "../views/Student/StudentThesisManagement.vue"
import StudentFormMenu from "../views/Student/StudentFormMenu.vue"
import AdvisorHomeComponent from "../views/Advisor/AdvisorHome.vue"
import AdvisorStudentApproval from "../views/Advisor/AdvisorStudentApproval.vue"
import AdvisorUpdateThesisTopics from "../views/Advisor/AdvisorUpdateThesisTopics.vue"
import AdvisorCompleteProgramPlanForms from "../views/Advisor/AdvisorCompleteProgramPlanForms.vue"
import AdvisorSubmitThesisCopies from "../views/Advisor/AdvisorSubmitThesisCopies.vue"
import AdvisorJuryRecommendation from "../views/Advisor/AdvisorJuryRecommendation.vue"
import AdvisorFinalThesisCopyDeadlineExtension from "../views/Advisor/AdvisorFinalThesisCopyDeadlineExtension.vue"
import DBRhome from "../views/DBR/DBRHome.vue"
import DBRRecommendAdvisor from "../views/DBR/DBRRecommendAdvisor.vue"
import Juryhome from "../views/Jury/JuryHome.vue"
import JuryThesisManagement from "../views/Jury/JuryThesisManagement.vue"
import IBDRhome from "../views/IBDR/IBDRHome.vue"
import GSEShome from "../views/GSES/GSESHome.vue"

import Slider from '@jeremyhamm/vue-slider'

import VueSidebarMenu from 'vue-sidebar-menu'

Vue.use(VueSidebarMenu)


Vue.use(Slider)
Vue.use(VueRouter)




export default new VueRouter({
    routes: [
        {
            path: '/',
            redirect: {
                name: "login"
            }
        },
        {
            path: "/login",
            name: "login",
            component: LoginComponent
        },
        {
            path: "/studenthome",
            name: "studenthome",
            component: StudentHomeComponent,
        },
        {
            path: "/student/advisor-selection",
            name: "StudenAdvisorSelection",
            component: StudenAdvisorSelection,
        },
        {
            path: "/student/program-plan-form",
            name: "StudentProgramPlanForm",
            component: StudentProgramPlanForm,
        },
        {
            path: "/student/thesis-management",
            name: "StudentThesisManagement",
            component: StudentThesisManagement,
        },
        {
            path: "/student/form-menu",
            name: "StudentFormMenu",
            component: StudentFormMenu,
        },
        {
            path: "/advisorhome",
            name: "advisorhome",
            component: AdvisorHomeComponent,
        },
        {
            path: "/advisor/student-approval",
            name: "AdvisorStudentApproval",
            component: AdvisorStudentApproval,
        },
        {
            path: "/advisor/update-thesis-topics",
            name: "AdvisorUpdateThesisTopics",
            component: AdvisorUpdateThesisTopics,
        },
        {
            path: "/advisor/complete-program-plan-forms",
            name: "AdvisorCompleteProgramPlanForms",
            component: AdvisorCompleteProgramPlanForms,
        },
        {
            path: "/advisor/submit-thesis-copies",
            name: "AdvisorSubmitThesisCopies",
            component: AdvisorSubmitThesisCopies,
        },
        {
            path: "/advisor/jury-recommendation",
            name: "AdvisorJuryRecommendation",
            component: AdvisorJuryRecommendation,
        },
        {
            path: "/advisor/final-thesis-copy-deadline-extension",
            name: "AdvisorFinalThesisCopyDeadlineExtension",
            component: AdvisorFinalThesisCopyDeadlineExtension,
        },
        {
            path: "/dbrhome",
            name: "dbrhome",
            component: DBRhome,
        },
        {
            path: "/DBR/recommend-advisor",
            name: "DBRRecommendAdvisor",
            component: DBRRecommendAdvisor,
        },
        {
            path: "/juryhome",
            name: "juryhome",
            component: Juryhome,
        },
        {
            path: "/ibdrhome",
            name: "ibdrhome",
            component: IBDRhome,
        },
        {
            path: "/gseshome",
            name: "gseshome",
            component: GSEShome,
        },
        {
            path: "/jury/thesis-management",
            name: "JuryThesisManagement",
            component: JuryThesisManagement,
        }
    ],
    //mode: 'history'
})