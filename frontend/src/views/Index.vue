<template>
  <div>
    <index-navbar />
    <section
      class="header relative pt-16 items-center flex h-screen max-h-860-px"
    >
      <div class="container mx-auto items-center flex flex-wrap">
        <div class="w-full md:w-8/12 lg:w-6/12 xl:w-6/12 px-4">
          <div class="pt-32 sm:pt-0">
            <h2 class="font-semibold text-4xl text-blueGray-600">
              REALMENTOR - это платформа для поиска вдохновителей и наставников
            </h2>
            <p class="mt-4 text-lg leading-relaxed text-blueGray-500">
              Прокачивай навыки
              с проверенными менторами и участвуй в коммерческих проектах <br/> Или хочешь делиться опытом
и помогать другим людям?
Заполни анкету и присоединяйся к нашей команде менторов</p>
            <div class="mt-12">
              <a
                href="#"
                target="_blank"
                class="get-started text-white font-bold px-6 py-4 rounded outline-none focus:outline-none mr-1 mb-1 bg-emerald-500 active:bg-emerald-600 uppercase text-sm shadow hover:shadow-lg ease-linear transition-all duration-150"
              >
                Найти ментора
              </a>
              <a
                href="#"
                class="github-star ml-1 text-white font-bold px-6 py-4 rounded outline-none focus:outline-none mr-1 mb-1 bg-blueGray-700 active:bg-blueGray-600 uppercase text-sm shadow hover:shadow-lg ease-linear transition-all duration-150"
                target="_blank"
              >
                Стать ментором
              </a>
            </div>
          </div>
        </div>
      </div>

      <img
        class="absolute top-0 b-auto right-0 pt-16 sm:w-6/12 -mt-48 sm:mt-0 w-10/12 max-h-860-px"
        :src="patternVue"
        alt="..."
      />
    </section>

    <section class="mt-48 md:mt-40 pb-40 relative bg-blueGray-100">
      <div
        class="-mt-20 top-0 bottom-auto left-0 right-0 w-full absolute h-20"
        style="transform: translateZ(0);"
      >
        <svg
          class="absolute bottom-0 overflow-hidden"
          xmlns="http://www.w3.org/2000/svg"
          preserveAspectRatio="none"
          version="1.1"
          viewBox="0 0 2560 100"
          x="0"
          y="0"
        >
          <polygon
            class="text-blueGray-100 fill-current"
            points="2560 0 2560 100 0 100"
          ></polygon>
        </svg>
      </div>
     
    </section>
    <footer-component />
  </div>

</template>
<script>
import IndexNavbar from "@/components/Navbars/IndexNavbar.vue";
import FooterComponent from "@/components/Footers/Footer.vue";

import patternVue from "@/assets/img/pattern_vue.png";
import componentBtn from "@/assets/img/component-btn.png";
import componentProfileCard from "@/assets/img/component-profile-card.png";
import componentInfoCard from "@/assets/img/component-info-card.png";
import componentInfo2 from "@/assets/img/component-info-2.png";
import componentMenu from "@/assets/img/component-menu.png";
import componentBtnPink from "@/assets/img/component-btn-pink.png";
import documentation from "@/assets/img/documentation.png";
import login from "@/assets/img/login.jpg";
import profile from "@/assets/img/profile.jpg";
import landing from "@/assets/img/landing.jpg";

export default {
  data() {
    return {
      patternVue,
      componentBtn,
      componentProfileCard,
      componentInfoCard,
      componentInfo2,
      componentMenu,
      componentBtnPink,
      documentation,
      login,
      profile,
      landing,
    };
  },
  components: {
    IndexNavbar,
    FooterComponent,
  },
};
</script>
