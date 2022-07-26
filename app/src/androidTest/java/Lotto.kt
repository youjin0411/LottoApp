import android.content.SharedPreferences

data class Lotto(var idx: Int, var text: String, var from: String = ""){
    companion object{
        fun saveToRreference(pref: SharedPreferences, idx: Int, text: String, from: String = "") : Lotto {
            var editor = pref.edit()

            editor.putString("$idx.text")
            editor.putString("$idx.from", from.trim())

            editor.apply()

            return Lotto(idx,text,from)
        }

        fun getLottoFromPreference(pref: SharedPreferences):MutableList<Lotto> {
            val lottos = mutableListOf<Lotto>()

            for (i in 0 until 20){
                val lottoText = pref.getString("$i.text","")!!
                val lottoFrom = pref.getString("$i.from","")!!

                if(lottoText.isNotBlank()){
                    lottos.add(Lotto(i,lottoText,lottoFrom))
                }
            }
            return lottos
        }
        fun removeLottoFromPreference(pref: SharedPreferences, idx: Int){
            val editor = pref.edit()

            editor.remove("$idx.text")
            editor.remove("$idx.from")
            editor.apply()
        }
    }
}

private fun SharedPreferences.Editor.putString(s: String) {

}
