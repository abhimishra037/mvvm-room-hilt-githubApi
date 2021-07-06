package c.com.githubrepolist.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import c.com.githubrepolist.R
import c.com.githubrepolist.model.GithubRepo

class GithubRepoListAdapter(
    private val repoList: List<GithubRepo>, private val listener: ItemClickListener
) : RecyclerView.Adapter<GithubRepoListAdapter.ViewHolder>() {


    interface ItemClickListener {
        fun onItemClick(name: String, lang: String)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.txtMovieTitle)
        fun bind(
            repoName: String,
            listener: ItemClickListener,
            language: String
        ) {

            textView.setOnClickListener {
                listener.onItemClick(repoName, language)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_movie_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = repoList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = repoList[position].name
        holder.bind(repoList[position].name, listener,repoList[position].language)


    }
}