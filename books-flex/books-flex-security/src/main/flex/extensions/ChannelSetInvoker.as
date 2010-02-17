package extensions {
import com.asfusion.mate.actionLists.IScope;
import com.asfusion.mate.actions.AbstractServiceInvoker;
import com.asfusion.mate.actions.IAction;

import com.asfusion.mate.core.SmartArguments;

import mx.messaging.ChannelSet;
import mx.rpc.AsyncResponder;
import mx.rpc.AsyncToken;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

public class ChannelSetInvoker extends AbstractServiceInvoker implements IAction {
    /*-.........................................channelSet..........................................*/
    public var _channelSet:ChannelSet;
    public function get channelSet():ChannelSet {
        return _channelSet;
    }

    public function set channelSet(value:ChannelSet):void {
        _channelSet = value;
    }

    /*-.........................................method..........................................*/
    public var _method:String;
    public function get method():String {
        return _method;
    }

    public function set method(value:String):void {
        _method = value;
    }

    /*-.........................................arguments..........................................*/
    private var _arguments:* = undefined;
    /**
     *  The property <code>arguments</code> allows you to pass an Object or an Array of objects when
     * calling the function defined in the property <code>method</code>.
     *  You can use an array to pass multiple arguments or use a simple Object if the
     * signature of the <code>method</code> has only one parameter.
     *
     *  @default undefined
     */
    public function get arguments():* {
        return _arguments;
    }

    public function set arguments(value:*):void {
        _arguments = value;
    }

    /*-.........................................constructor..........................................*/
    public function ChannelSetInvoker() {
        this.debug = true;
    }

    override protected function run(scope:IScope):void {
        var argumentList:Array = (new SmartArguments()).getRealArguments(scope, this.arguments);
        innerHandlersDispatcher = channelSet;

        if (method == "login") {
            if (!channelSet.authenticated) {
                var loginToken:AsyncToken = channelSet.login(argumentList[0], argumentList[1]);
                loginToken.addResponder(new AsyncResponder(
                        function(event:ResultEvent, token:Object = null):void {
                            scope.lastReturn = event.result;
                            innerHandlersDispatcher.dispatchEvent(event);
                        },
                        function(event:FaultEvent, token:Object = null):void {
                            scope.lastReturn = event.fault;
                            innerHandlersDispatcher.dispatchEvent(event);
                        }));
            }
        } else if (method == "logout") {
            var logoutToken:AsyncToken = channelSet.logout();
            logoutToken.addResponder(new AsyncResponder(
                    function(event:ResultEvent, token:Object = null):void {
                        innerHandlersDispatcher.dispatchEvent(event);
                    },
                    function(event:FaultEvent, token:Object = null):void {
                        innerHandlersDispatcher.dispatchEvent(event);
                    }));
        } else if (method == "authenticated") {
            var isAuthenticated:Boolean = channelSet.authenticated;
            scope.lastReturn = isAuthenticated;
            var event:ResultEvent = new ResultEvent("user is authenticated?", false, true, isAuthenticated);
            innerHandlersDispatcher.dispatchEvent(event);
        }
    }

    /**
     * You should use this technique only for objects that can by of the form { ... } in de mxml config
     * Username and password are a bad example. They are not constant to the component, they can vary for
     * the method calls. Therefore they must be provided with a different mechanism.
     * @param scope
     * @return
     */
    override protected function prepare(scope:IScope):void {
        super.prepare(scope);
        currentInstance = channelSet;
    }

    override protected function complete(scope:IScope):void {
        if (this.resultHandlers && resultHandlers.length > 0) {
            this.createInnerHandlers(scope, ResultEvent.RESULT, resultHandlers);
        }
        if (this.faultHandlers && faultHandlers.length > 0) {
            this.createInnerHandlers(scope, FaultEvent.FAULT, faultHandlers);
        }
    }

}
}