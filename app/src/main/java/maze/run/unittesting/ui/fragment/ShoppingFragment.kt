package maze.run.unittesting.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_shopping.view.*
import maze.run.unittesting.R


class ShoppingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_shopping, container, false)

        view.fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(R.id.action_shoppingFragment_to_addShoppingItemFragment)
        }
        return view
    }

}