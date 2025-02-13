package net.iamtakagi.kodaka

import net.iamtakagi.kodaka.annotation.CommandMeta
import org.apache.commons.lang3.StringUtils
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand
import org.bukkit.entity.Player
import java.util.*

internal class KodakaCommand(label: String, private val executor: KodakaExecutor) : BukkitCommand(label) {

    companion object {
        fun getHierarchicalLabel(clazz: Class<Any>, list: MutableList<String>): List<String> {
            val toReturn: MutableList<String> = ArrayList()
            val superClass = clazz.superclass

            if (superClass != null) {
                if (clazz.superclass.isAnnotationPresent(CommandMeta::class.java)) {
                    list.addAll(getHierarchicalLabel(superClass, list))
                }
            }

            val meta = clazz.getAnnotation(CommandMeta::class.java) as CommandMeta

            if (list.isEmpty()) {
                toReturn.addAll(Arrays.asList(*meta.label))
            } else {
                for (prefix in list) {
                    for (label in meta.label) {
                        toReturn.add("$prefix $label")
                    }
                }
            }

            return toReturn
        }
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        val message = arrayOf(commandLabel, *args)
        var label = commandLabel

        for (i in message.size downTo 1) {
            label = StringUtils.join(message, " ", 0, i)

            if (executor.commands[label.toLowerCase()] != null) {
                break
            }
        }

        val correctArguments = ArrayList<String>(args.toMutableList().subList(label.split(' ').size - 1, args.size))

        return executor.execute(sender, executor.commandMap.getCommand(label)!!, label.toLowerCase(), correctArguments.toTypedArray())
    }


    /*
    致命的なエラーが発生するため、実装を取り消しています
    override fun tabComplete(sender: CommandSender, commandLabel: String, args: Array<out String>): MutableList<String> {
        val message = arrayOf(commandLabel, *args)
        var label: String
        var binding: KodakaExecutor.CommandBinding? = null

        for (i in message.size downTo 1) {
            label = StringUtils.join(message, " ", 0, i)

            binding = executor.commands[label.toLowerCase()]
            if (binding != null) {
                break
            }
        }

        if (binding == null) return mutableListOf()

        val instance = binding.command
        val arg = args.last()

        outer@ for (method in binding.methods) {
            val parameters = method.parameters

            if (parameters[0].type is Player && sender !is Player) continue
            if (method.declaringClass != instance.javaClass) continue

            /**
             * Prioritizes the method that is exact type of sender + lowest argument count
             */
            for (other in binding.methods) {
                if (other != method) {

                    if (method.parameterCount == other.parameterCount) {
                        if (parameters[0].type is CommandSender && sender is Player && other.parameters[0].type is Player) {
                            continue@outer
                        }
                    }

                    if (method.parameterCount - 1 != args.size) {
                        if (method.parameterCount < other.parameterCount) {
                            continue@outer
                        }
                    }

                }
            }

            val parameter = parameters[args.indexOf(arg)] // Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1 at net.iamtakagi.kodaka.KodakaCommand.tabComplete(KodakaCommand.kt:103)
            val adapter = executor.adapters[parameter.type]

            if (adapter == null) {
                val toReturn = ArrayList<String>()

                Bukkit.getOnlinePlayers().filter { it.name.toLowerCase().startsWith(arg.toLowerCase()) }.forEach {
                    toReturn.add(it.name)
                }

                return toReturn
            }

            return adapter.onTabComplete(arg)
        }

        return mutableListOf()
    }
    */
}